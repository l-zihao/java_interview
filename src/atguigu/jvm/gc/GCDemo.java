package atguigu.jvm.gc;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/24 15:23
 */

import java.util.Random;

/**
 * 1. -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC    (DefNew+Tenured)
 *
 * 2. -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC    (ParNew+Tenured)
 *  备注情况：Java HotSpot(TM) 64-Bit Server VM warning:
 *  Using the ParNew young collector with Serial old collector is deprecated
 *  and will likely be removed in a future release
 *
 * 3. -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC  (PSYoungGen+ParOldGen)
 *
 * 4.1 -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelOldGC  (PSYoungGen+ParOldGen)
 * 4.2 不加就是默认UseParalleclGC：-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags (PSYoungGen+ParOldGen)
 *
 * 5. -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC  (par new generation + concurrent mark-sweep)
 *
 * 6. -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC  (G1)
 *
 * 7. (理论知道即可，实际中已经被优化掉，没有了。)
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialOldGC
 *
 * 下面是故意繁琐配置，主要是为了学习，一般生产不这么配置：
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC -XX:+UseParallelOldGC (PSYoungGen+ParGen)
 *
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC -XX:+UseConcMarkSweepGC (par new generation + concurrent mark-sweep generation)
 */
public class GCDemo {

    public static void main(String[] args) {
        System.out.println("**********GCDemo hello");
        try{
            String str = "demo";
            while (true){
                str += str + new Random().nextInt(77777777) + new Random().nextInt(88888888);
                str.intern();
            }
        }catch(Throwable e){
            e.printStackTrace();
        }
    }
}
