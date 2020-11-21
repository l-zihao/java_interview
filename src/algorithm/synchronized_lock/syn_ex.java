package algorithm.synchronized_lock;

import java.util.concurrent.TimeUnit;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/9 12:26
 */
public class syn_ex {
    int count = 0;
    synchronized void m(){
        System.out.println(Thread.currentThread().getName() + " start");
        while (true){
            count++;
            System.out.println(Thread.currentThread().getName() + " count=" + count);
            //暂停一会儿线程
            try{ TimeUnit.SECONDS.sleep(1); }catch (InterruptedException e){ e.printStackTrace(); }
            if(count == 5){
                int i = 1/0; //此处抛出异常，锁将被释放，要想不被释放，可以在这里进行catch，然后循环继续
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        syn_ex syn_ex = new syn_ex();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                syn_ex.m();
            }
        };

        new Thread(r, "t1").start();

        //暂停一会儿线程
        try{ TimeUnit.SECONDS.sleep(3); }catch (InterruptedException e){ e.printStackTrace(); }

        new Thread(r, "t2").start();
    }
}
