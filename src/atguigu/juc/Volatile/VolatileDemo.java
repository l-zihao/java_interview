package atguigu.juc.Volatile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/15 17:10
 */

class MyData{

    int number = 0;
    //volatile int number = 0;

    public void addTo60(){
        this.number = 60;
    }

    //请注意，此时number前面时加了volatile关键字修饰的，volatile不保证原子性
    public void addPlusPlus(){
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addMyAtomic(){
        atomicInteger.getAndIncrement();
    }
}

/**
 * 1 验证volatile的可见性
 *  1.1 假如 int number=0； number变量之前没有添加volatile关键字修饰
 *  1.2 添加了volatile，可以解决可见性问题
 *
 * 2 验证volatile2不保证原子性
 *  2.1 原子性指的是什么意思？
 *      不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者被分割，需要具体完整
 *      要么同时成功，要么同时失败
 *  2.2 volatile不保证原子性的案例演示
 *  2.3 why?
 *  2.4 如何解决原子性？
 *       加synchronized
 *       使用juc下AtomicInteger
 */

public class VolatileDemo {

    public static void main(String[] args) {

        seeOkByVolatile();

        MyData myData = new MyData();

        for(int i=1; i<=20; i++){
           new Thread(()->{
               for (int j = 0; j < 1000; j++) {
                   myData.addPlusPlus();
                   myData.addMyAtomic();
               }
           }, String.valueOf(i)).start();
       }

        //需要等待上面20个线程都全部计算完成后，再用main线程取得最终结果值看是多少？
        while(Thread.activeCount() > 2){ //默认后台有main线程，GC线程
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + " int type,finally number value: " + myData.number);
        System.out.println(Thread.currentThread().getName() + " AtomicInteger,finally number value: " + myData.atomicInteger);
    }


    //volatile可以保证可见性，及时通知其他线程，主物理内存的值已经被修改
    private static void seeOkByVolatile() {
        MyData myData = new MyData();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " is start");

            try { //睡眠3秒，保证在执行addTo60方法时，main线程已经执行到while循环，已取过number的值
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + " update number: " + myData.number);

        }, "AAA").start();


        System.out.println(myData.number);
        while (myData.number == 0){
            //main线程就一直在这里等待循环，直到number值不再等于零
        }
        System.out.println("main Thread is over, number: " + myData.number);
    }

}
