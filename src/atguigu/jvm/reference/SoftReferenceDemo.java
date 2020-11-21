package atguigu.jvm.reference;

import java.lang.ref.SoftReference;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/23 10:48
 */
public class SoftReferenceDemo {
    /**
     * 内存够用的时候就保留，不够用的时候就回收
     */
    public static void soft_Ref_Memory_Enough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        System.gc();
        System.out.println("=========================");

        System.out.println(o1);
        System.out.println(softReference.get());

        /**
         * java.lang.Object@1b6d3586
         * java.lang.Object@1b6d3586
         * =========================
         * null
         * java.lang.Object@1b6d3586
         */
    }

    /**
     * JVM配置，故意产生大对象并配置小内存，让它内存不够用了导致OOM，看软引用的回收情况
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    public static void soft_Ref_Memory_NotEnough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;

        try{
            byte[] bytes = new byte[30 * 1024 * 1024];
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            System.out.println("=========================");
            System.out.println(o1);
            System.out.println(softReference.get());
        }
    }

    public static void main(String[] args) {
        soft_Ref_Memory_Enough();

        soft_Ref_Memory_NotEnough();
    }
}
