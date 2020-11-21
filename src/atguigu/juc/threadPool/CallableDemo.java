package atguigu.juc.threadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/19 13:29
 */

class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t **********come in Callable");
        //暂停一会儿线程
        try{ TimeUnit.SECONDS.sleep(3); }catch (InterruptedException e){ e.printStackTrace(); }
        return 1024;
    }
}

/**
 * java中，获得多线程的方法：
 * 1. 继承Thread类
 * 2. 实现Runnable接口
 * 3. 实现Callable接口
 * 4. 线程池
 *
 * 实现Runnable接口与实现Callable接口的区别：
 * 1. Runnable接口没返回值，Callable接口有返回值
 * 2. Runnable接口不会抛异常，Callable接口会抛异常
 * 3. 实现Runnable接口要重写run()方法，实现Callable接口则重写call()方法
 *
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Thread t1 = new Thread();
        //t1.start();
        //Thread(Runnable target, String name) Allocates a new Thread object

        //两个线程，用过main主线程，一个AAfutureTask

        //FutureTask(Callable<V> callable)
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        new Thread(futureTask, "AA").start();
        new Thread(futureTask, "BB").start();
        //int result02 = futureTask.get();

        System.out.println(Thread.currentThread().getName() + "************");
        int result01 = 100;

        //while (!futureTask.isDone()){
        //
        //}

        int result02 = futureTask.get(); //要求获得Callable线程的计算结果，如果没有计算完成就要去强求，会导致堵塞，直到计算完成
        System.out.println("*******result: " + (result01 + result02));
    }
}
