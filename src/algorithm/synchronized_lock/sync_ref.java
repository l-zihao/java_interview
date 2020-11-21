package algorithm.synchronized_lock;

import java.util.concurrent.TimeUnit;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/9 15:44
 */
public class sync_ref {

    /*final*/ Object o = new Object();

    void m(){
        synchronized (o){
            while (true){
                //暂停一会儿线程
                try{ TimeUnit.SECONDS.sleep(1); }catch (InterruptedException e){ e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        sync_ref sync_ref = new sync_ref();
        //启动第一个程序
        new Thread(sync_ref::m, "t1").start();
        //暂停一会儿线程
        try{ TimeUnit.SECONDS.sleep(3); }catch (InterruptedException e){ e.printStackTrace(); }
        //启动第二个程序
        Thread t2 = new Thread(sync_ref::m, "t2");

        sync_ref.o = new Object(); //锁定对象发生改变，所以t2线程得以执行，如果注释掉这句话，线程2将永远得不到执行机会

        t2.start();
    }
}
