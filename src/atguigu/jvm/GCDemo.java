package atguigu.jvm;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/24 15:23
 */

import java.util.Random;

/**
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+UseSerialOldGC
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
