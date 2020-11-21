package Singleton;

import java.util.concurrent.*;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/9 23:15
 */
public class Singleton4 {

    private static Singleton4 instance;

    private Singleton4(){}

    public static Singleton4 getInstance(){
        if(instance == null){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new Singleton4();
        }
        return instance;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
/*
        Singleton4 s1 = Singleton4.getInstance();
        Singleton4 s2 = Singleton4.getInstance();
        System.out.println(s1 == s2);
*/

        Callable<Singleton4> c = new Callable<Singleton4>() {
            @Override
            public Singleton4 call() throws Exception {
                return Singleton4.getInstance();
            }
        };

        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Singleton4> f1 = es.submit(c);
        Future<Singleton4> f2 = es.submit(c);

        Singleton4 s1 = f1.get();
        Singleton4 s2 = f2.get();
        System.out.println(s1 == s2);

    }
}
