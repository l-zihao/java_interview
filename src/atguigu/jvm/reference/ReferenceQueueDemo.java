package atguigu.jvm.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/23 11:59
 */
public class ReferenceQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference weakReference = new WeakReference(o1, referenceQueue);
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("=========================");
        o1 = null;
        System.gc();
        Thread.sleep(500);

        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());

        /**
         * java.lang.Object@1b6d3586
         * java.lang.Object@1b6d3586
         * null
         * =========================
         * null
         * null
         * java.lang.ref.WeakReference@4554617c
         */
    }
}
