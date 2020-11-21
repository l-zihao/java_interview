package algorithm.synchronized_lock;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/9 11:35
 */
public class T02 {

    private int count = 10;

    public void m(){
        synchronized (this){ //任何线程要执行下面的代码，必须先拿到this的锁
            count--;
            System.out.println(Thread.currentThread().getName() + " count=" + count);
        }
    }

}
