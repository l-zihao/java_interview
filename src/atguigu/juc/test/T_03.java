package atguigu.juc.test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/21 23:17
 */
public class T_03 {

    private BlockingQueue queue = new LinkedBlockingQueue();
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private volatile boolean Flag = true;


    void product() throws InterruptedException {

        String data= null;

        boolean retValue;

        while (Flag){
            data = atomicInteger.incrementAndGet() + "";
            retValue = queue.offer(data, 2, TimeUnit.SECONDS);
            if(retValue){
                System.out.println(Thread.currentThread().getName() + "insert success: " + data);
            }else {
                System.out.println(Thread.currentThread().getName() + "insert fail: " + data);
            }

            //暂停一会儿线程
            try{ TimeUnit.SECONDS.sleep(1); }catch (InterruptedException e){ e.printStackTrace(); }
        }
    }


    void consume() throws InterruptedException {
        String data = null;
        while (Flag){
            data = (String) queue.poll(2, TimeUnit.SECONDS);
            if(data == null || data.equalsIgnoreCase("")){
                Flag = false;
                System.out.println(Thread.currentThread().getName() + "get fail, exit");
            }else {
                System.out.println(Thread.currentThread().getName() + "get success: " + data);
            }
        }
    }

    void stop() throws Exception{
        this.Flag = false;
    }


    public static void main(String[] args) throws Exception {

        T_03 t_03 = new T_03();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 生产线程启动");
            try {
                t_03.product();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Prod").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 消费线程启动");
            try {
                t_03.consume();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Consumer").start();

        //暂停一会儿线程
        try{ TimeUnit.SECONDS.sleep(5); }catch (InterruptedException e){ e.printStackTrace(); }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("5秒钟时间到，大老板main线程叫停，活动结束");
        t_03.stop();

    }
}
