package atguigu.juc.test;

import java.util.concurrent.TimeUnit;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/20 11:56
 */

class Re{
    volatile int number = 0;

    public void addPlusPlus(){
        number++;
    }
}

public class T_01 {

    public static void main(String[] args) throws InterruptedException {

        //volatile保证可见性
        //testVolatileSeeOk();

        //volatie不保证原子性
        testVolatileNoAtomic();

        //volatile禁止指令重排
        //验证乱序执行
        //testCodeSort();
    }

    /*private static void testCodeSort() throws InterruptedException {
        int i = 0;
        for(;;){
            i++;
            x = 0; y = 0;
            a = 0; b = 0;

            Thread one = new Thread(new Runnable() {
                @Override
                public void run() {
                    a = 1;
                    x = b;
                }
            });

            Thread two = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = 1;
                    y = a;
                }
            });

            one.start();two.start();
            one.join();two.join();

            String result = "第" + i + "次 (" + x + "," + y + ") ";
            if(x == 0 && y == 0){
                System.err.println(result);
                break;
            }else {
                //System.out.println(result);
            }
        }
    }*/

    private static void testVolatileNoAtomic() {
        Re re = new Re();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    re.addPlusPlus();
                }
            }, "A"+i).start();
        }

        //判断当前存活的线程数  GC线程默认一直存活
        while (Thread.activeCount()>2){

        }

        System.out.println(Thread.currentThread().getName() + "\tnumber: " + re.number);
    }

    private static void testVolatileSeeOk() {
        Re myData = new Re();

        new Thread(() -> {
            //暂停一会儿线程
            try{ TimeUnit.SECONDS.sleep(1); }catch (InterruptedException e){ e.printStackTrace(); }
            myData.number = 60;
            //暂停一会儿线程
            try{ TimeUnit.SECONDS.sleep(1); }catch (InterruptedException e){ e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + "\tend");
        }, "AAA").start();

        while(myData.number == 0){

        }
        System.out.println(Thread.currentThread().getName() + "\tend");
    }

}
