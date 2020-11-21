package algorithm.synchronized_lock;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/9 11:35
 */
public class T05 {

    private static int count = 10;

    public static /*synchronized*/ void m(){
        synchronized (T05.class){
            count--;
            System.out.println(Thread.currentThread().getName() + " count=" + count);
        }
    }
}
