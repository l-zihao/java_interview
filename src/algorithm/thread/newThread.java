package algorithm.thread;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/9 10:57
 */

/**
 * 创建线程的三种方式
 * 1. extend Thread 重写run()方法
 * 2. implement Runnable 重写run()方法
 * 3. Executors.newThread
 */
public class newThread {

    static class MyThread extends Thread{
        @Override
        public void run(){
            System.out.println(Thread.currentThread().getName());
        }
    }

    static class MyRun implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        new MyThread().start();

        new Thread(new MyRun()).start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        }).start();
    }

}
