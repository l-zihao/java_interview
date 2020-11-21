package algorithm.synchronized_lock;

import java.util.concurrent.TimeUnit;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/9 12:12
 */
public class Reentrant {
    synchronized void m1(){
        System.out.println("m1 start");
        //暂停一会儿线程
        try{ TimeUnit.SECONDS.sleep(1); }catch (InterruptedException e){ e.printStackTrace(); }
        m2();
        System.out.println("m1 end");
    }

    synchronized void m2(){
        //暂停一会儿线程
        try{ TimeUnit.SECONDS.sleep(2); }catch (InterruptedException e){ e.printStackTrace(); }
        System.out.println("m2");
    }

    public static void main(String[] args) {
        new Reentrant().m1();
    }
}
