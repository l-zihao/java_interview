package Singleton;

import java.util.concurrent.*;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/9 23:28
 */
public class Singleton5 {

    private volatile static Singleton5 instance;

    private Singleton5(){}

    //DCL（双端检锁）机制不一定线程安全，原因是有指令重排序的存在,加入volatile可以禁止指令重排
    public static Singleton5 getInstance(){
        if(instance == null){
            synchronized (Singleton5.class){
                if(instance == null){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance = new Singleton5();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
/*
        Singleton5 s1 = Singleton5.getInstance();
        Singleton5 s2 = Singleton5.getInstance();
        System.out.println(s1 == s2);
*/

        Callable<Singleton5> c = new Callable<Singleton5>() {
            @Override
            public Singleton5 call() throws Exception {
                return Singleton5.getInstance();
            }
        };

        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Singleton5> f1 = es.submit(c);
        Future<Singleton5> f2 = es.submit(c);

        Singleton5 s1 = f1.get();
        Singleton5 s2 = f2.get();
        System.out.println(s1 == s2);

    }

}
