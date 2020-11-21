package atguigu.juc.threadPool;

import java.util.concurrent.*;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/19 14:44
 */

/**
 *
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {
        //threadPoolInit(); //使用Executors.newFixedThreadPool(5);创建线程池

        System.out.println(Runtime.getRuntime().availableProcessors()); //查看CPU核数

        //自定义线程池
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());


        try{
            for (int i = 1; i <= 9; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            threadPool.shutdown();
        }

    }

    private static void threadPoolInit() {
        //Array Arrays   List<String> list = Arrays.asList("a", "b", "c");
        //Collection Collections
        //Executor Executors

        ExecutorService threadPool = Executors.newFixedThreadPool(5); //一池5个处理线程
        //ExecutorService threadPool = Executors.newSingleThreadExecutor(); //一池1个处理线程
        //ExecutorService threadPool = Executors.newCachedThreadPool(); //一池N个处理线程

        //模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
        try{
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
                //暂停一会儿线程
                try{ TimeUnit.MILLISECONDS.sleep(200); }catch (InterruptedException e){ e.printStackTrace(); }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            threadPool.shutdown();
        }
    }

}
