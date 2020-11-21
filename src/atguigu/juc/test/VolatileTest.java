package atguigu.juc.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/6/2 10:11
 */

/**
 * jmm -> 可见性 原子性 有序性(happens-before原则 指令重排) -> volatile -> 内存屏障
 *
 * volatile java虚拟机提供的一种轻量级的同步机制
 * 1. 保证可见性
 * 2. 不保证原子性
 * 3. 禁止指令重排
 *
 * 指令屏障
 * 1. 阻止屏障两侧的指令重排序 -> 禁止指令重排
 * 2. 强制把写缓冲区/高速缓存中的脏数据写回主内存（write），让缓存中相应的数据失效（read） -> 保证可见性
 *
 *  -> Load指令屏障 Store屏障指令
 *  -> 写操作之后加入Store屏障指令 将工作内存中的变量重新刷回主内存 让其他线程可见
 *  -> 读操作之前加入Load屏障指令 可以让工作内存中的数据失效，强制将主内存中变量最新的值拷贝回工作内存
 *
 *  java的四种内存屏障
 *  -> LoadLoad: Load1; LoadLoad; Load2，在Load2及后续读取操作要读取的数据被访问前，保证Load1要读取的数据被读取完毕
 *  -> StoreStore: Store1; StoreStore; Store2，在Store2及后续写入操作执行前，保证Store1的写入操作对其它处理器可见
 *  -> LoadStore: Load1; LoadStore; Store2，在Store2及后续写入操作被刷出前，保证Load1要读取的数据被读取完毕
 *  -> StoreLoad: Store1; StoreLoad; Load2，在Load2及后续所有读取操作执行前，保证Store1的写入对所有处理器可见
 */
class MYData{
    volatile int number = 0;

    /**
     * CAS算法 同步非阻塞算法 compare and swap
     * -> unsafe value(volatile)
     * -> ABA问题
     * -> AtomicReference
     * -> AtmomicStampReference 时间戳原子引用 vllue stamp
     */
    AtomicInteger atomicInteger = new AtomicInteger(0);

    public void addTo60(){
        this.number = 60;
    }

    public void addPlusPlus(){
        //1. getfield
        //2. iconst_1
        //3. iadd
        this.number++; //number++在多线程环境下是非线程安全的，不具有原子性
    }

    public void increment(){
        atomicInteger.getAndIncrement();
    }
}

public class VolatileTest {

    public static void main(String[] args) {
        //testVisible();
        MYData myData = new MYData();

        for(int i=1; i<=20; i++){
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                    myData.increment();
                }
            }, String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2){  //main gc

        }

        System.out.println(Thread.currentThread().getName() + "\t number: " + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t atomicInteger: " + myData.atomicInteger);
    }

    private static void testVisible() {
        MYData myData = new MYData();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t start");
            //暂停一会儿线程
            try{ TimeUnit.SECONDS.sleep(1); }catch (InterruptedException e){ e.printStackTrace(); }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + "\t number:" + myData.number);
            //暂停一会儿线程
            try{ TimeUnit.SECONDS.sleep(1); }catch (InterruptedException e){ e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + "\t end");
        }, "AAA").start();

        while (myData.number == 0){

        }

        System.out.println(Thread.currentThread().getName() + "\t number:" + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t end");
    }

}
