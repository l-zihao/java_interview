package atguigu.juc.DeadLock;

import java.util.concurrent.TimeUnit;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/20 11:12
 */

class HoldLockThread implements Runnable {

    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t 自己持有锁" + lockA + "尝试获得" + lockB);
            //暂停一会儿线程
            try{ TimeUnit.SECONDS.sleep(2); }catch (InterruptedException e){ e.printStackTrace(); }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t 自己持有锁" + lockB + "尝试获得" + lockA);
            }
        }
    }
}

public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA, lockB), "threadAAA").start();
        new Thread(new HoldLockThread(lockB, lockA), "threadBBB").start();

        /**
         * linux ps -ef|grep xxxx
         *
         * window下的java运行程序  也有类似ps的查看进程的命令，但是目前我们需要查看的只是java
         * jps = java ps    jps -l
         */
    }
}
