package atguigu.jvm.reference;

import java.lang.ref.WeakReference;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/23 11:01
 */
public class WeakReferenceDemo {

    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference weakReference = new WeakReference(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());

        o1 = null;
        System.gc();
        System.out.println("=========================");

        System.out.println(o1);
        System.out.println(weakReference.get());

        /**
         * java.lang.Object@1b6d3586
         * java.lang.Object@1b6d3586
         * =========================
         * null
         * null
         */
    }
}
