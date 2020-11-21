package atguigu.juc.Queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/18 23:08
 */
class ShareData{  //资源类
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception{
        lock.lock();
        try{
            //1. 判断  (多线程判断用while，不能用if)
            //如果用if 当signalAll()方法执行后，由于if只执行一次，其它所有正在执行await()方法的线程都会通过，
            //不会继续执行await()方法判断，而是继续执行下面的代码
            while(number != 0){
                //等待，不能生产
                condition.await();
            }
            //2. 干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //3. 通知唤醒
            condition.signal(); //不能用signal()方法，因为如果用signal()方法唤醒了一个不满足继续执行下去的线程
                                // （比如一个刚执行完increment()方法的线程唤醒了另外一个执行increment()方法的线程），那程序就会出现死锁
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public void decrement() throws Exception{
        lock.lock();
        try{
            //1. 判断
            while(number == 0){
                //等待，不能生产
                condition.await();
            }
            //2. 干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //3. 通知唤醒
            condition.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}

/**
 * 题目：一个初始值为零的变量，两个线程对其交替操作，一个加1一个减1，来5轮
 *
 * 1. 线程  操作(方法)  资源类
 * 2. 判断  干活  通知
 * 3。 防止虚假唤醒机制
 */
public class ProdConsumer_TraditionDemo {

    public static void main(String[] args) {

        ShareData shareData = new ShareData();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "CC").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "DD").start();
    }
}
