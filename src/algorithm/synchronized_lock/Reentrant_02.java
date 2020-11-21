package algorithm.synchronized_lock;

import java.util.concurrent.TimeUnit;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/9 12:19
 */
public class Reentrant_02 {

    synchronized void m(){
        System.out.println("m start");
        //暂停一会儿线程
        try{ TimeUnit.SECONDS.sleep(1); }catch (InterruptedException e){ e.printStackTrace(); }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        new child().m();
    }
}

class child extends Reentrant_02{
    @Override
    synchronized void m(){
        System.out.println("child start");
        super.m();
        System.out.println("child end");
    }
}
