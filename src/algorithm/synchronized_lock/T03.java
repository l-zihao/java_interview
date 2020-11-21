package algorithm.synchronized_lock;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/9 11:35
 */
public class T03 {

    private int count = 10;

    public synchronized void m(){ //等同于在方法的代码执行时要synchronized(this)
        count--;
        System.out.println(Thread.currentThread().getName() + " count=" + count);
    }

}
