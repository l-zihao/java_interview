package atguigu.jvm.oom;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/23 13:37
 */
public class StackOverflowErrorDemo {

    public static void main(String[] args) {
        stackOverflowError();

    }

    private static void stackOverflowError() {
        stackOverflowError();  //Exception in thread "main" java.lang.StackOverflowError
    }
}
