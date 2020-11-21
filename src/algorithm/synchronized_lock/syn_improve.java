package algorithm.synchronized_lock;

import java.util.concurrent.TimeUnit;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/9 15:12
 */
public class syn_improve {

    int count = 0;

    synchronized void m1(){
        //do sth need not sync
        //暂停一会儿线程
        try{ TimeUnit.SECONDS.sleep(2); }catch (InterruptedException e){ e.printStackTrace(); }

        //业务逻辑中只有下面这句需要sync，这时不应该给整个方法上锁
        count++;

        //do sth need not sync
        //暂停一会儿线程
        try{ TimeUnit.SECONDS.sleep(2); }catch (InterruptedException e){ e.printStackTrace(); }
    }

    void m2(){
        //do sth need not sync
        //暂停一会儿线程
        try{ TimeUnit.SECONDS.sleep(2); }catch (InterruptedException e){ e.printStackTrace(); }

       synchronized (this){
           //业务逻辑中只有下面这句需要sync，这时不应该给整个方法上锁
           count++;
       }

        //do sth need not sync
        //暂停一会儿线程
        try{ TimeUnit.SECONDS.sleep(2); }catch (InterruptedException e){ e.printStackTrace(); }
    }
}
