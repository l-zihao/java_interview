package atguigu.jvm.oom;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/23 13:50
 */
public class JavaHeapSpaceDemo {

    public static void main(String[] args) {
        byte[] bytes = new byte[80 * 1024 *1024];//80Mb
        //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
    }
}
