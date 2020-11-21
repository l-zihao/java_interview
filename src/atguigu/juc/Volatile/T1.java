package atguigu.juc.Volatile;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/15 21:39
 */
public class T1 {

    volatile int n = 0;

    public void add(){
        n++;
    }

}
