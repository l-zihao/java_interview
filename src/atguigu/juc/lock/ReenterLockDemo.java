package atguigu.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/17 16:19
 */
class Phone implements Runnable{

    public synchronized void sendSMS() throws Exception{
        System.out.println(Thread.currentThread().getName() + "\t invoked sendSMS");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getName() + "\t invoked sendEmail");
    }

    //==============================

    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    public void get(){
        lock.lock();
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\t invoked get");
            set();
        }finally {
            lock.unlock();
            lock.unlock();
        }
    }

    public void set(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\t invoked set");
        }finally {
            lock.unlock();
        }
    }
}

/**
 * case one Synchronized是一个典型的可重入锁
 * t1	 invoked sendSMS        t1线程在外层方法获取锁的时候
 * t1	 invoked sendEmail      t1在进入内层方法会自动获取锁
 * t2	 invoked sendSMS
 * t2	 invoked sendEmail
 *
 * case two ReentrantLock是一个典型的可重入锁
 * Thread-0	 invoked get
 * Thread-0	 invoked set
 * Thread-1	 invoked get
 * Thread-1	 invoked set
 */
public class ReenterLockDemo {

    public static void main(String[] args) {

        Phone phone = new Phone();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t1").start();


        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t2").start();

        //暂停一会儿线程
        try{ TimeUnit.SECONDS.sleep(1); }catch (InterruptedException e){ e.printStackTrace(); }
        System.out.println();
        System.out.println();
        System.out.println();

        Thread t3 = new Thread(phone);
        Thread t4 = new Thread(phone);

        t3.start();
        t4.start();
    }
}
