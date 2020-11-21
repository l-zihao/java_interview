package atguigu.jvm.gc;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/22 16:32
 */

/**
 * 如何查看一个正在运行中的java程序，它的某个jvm参数如何开启？具体值是多少？
 *   jps  查看java后台进程
 *     Linux ps
 *     window java  java ps
 *   jinfo  查看正在运行的java进程的信息
 *     jinfo -flag PrintGCDetails 进程编号
 *     jinfo -flag MetaspaceSize 进程编号
 *
 */
public class HelloGC {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("********HelloGC");

        Thread.sleep(Integer.MAX_VALUE);
    }

}
