package atguigu.juc.lock;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/19 10:38
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：多线程之间按顺序调用，实现A->B->三个线程启动，要求如下：
 * AA打印5次，BB打印10次，CC打印15次
 * 紧接着
 * AA打印5次，BB打印10次，CC打印15次
 * ......
 * 来10轮
 *
 *
 * synchronized
 *   9: monitorenter  锁监控器
 *  10: aload_1
 *  11: monitorexit   正常退出
 *  12: goto
 *  15: astore_2
 *  16: aload_1
 *  17: monitorexit   异常退出
 *
 * ReentrantLock
 *   new           #3  // class java/util/concurrent/locks/ReentrantLock
 */

class ShareResource{
    private int number = 1; //A:1 B:2 C:3
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition(); //备用钥匙1
    private Condition c2 = lock.newCondition(); //备用钥匙2
    private Condition c3 = lock.newCondition(); //备用钥匙3

    public void print5(){
        lock.lock();
        try{
            //1. 判断
            while (number != 1){
                c1.await();
            }
            //2. 干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3. 通知
            number = 2;
            c2.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try{
            //1. 判断
            while (number != 2){
                c2.await();
            }
            //2. 干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3. 通知
            number = 3;
            c3.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try{
            //1. 判断
            while (number != 3){
                c3.await();
            }
            //2. 干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3. 通知
            number = 1;
            c1.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}

public class SyncAndReentrantLockDemo {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print5();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print10();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print15();
            }
        }, "C").start();

        /*
        for(int i=1; i<=10; i++){
            new Thread(()->{
                shareResource.print5();
            }, "A" + String.valueOf(i)).start();
        }

        for(int i=1; i<=10; i++){
            new Thread(()->{
                shareResource.print10();
            }, "B" + String.valueOf(i)).start();
        }

        for(int i=1; i<=10; i++){
            new Thread(()->{
                shareResource.print15();
            }, "C" + String.valueOf(i)).start();
        }
        */
    }
}
