package algorithm.synchronized_lock;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/9 15:53
 */
public class sync_str {

    String s1 = "Hello";
    String s2 = "Hello";

    void m1(){
        synchronized (s1){

        }
    }

    void m2(){
        synchronized (s2){

        }
    }
}
