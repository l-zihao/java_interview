package atguigu.jvm.gc;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/22 16:04
 */

public class GCRootDemo {

    private byte[] byteArray = new byte[100 * 1024 *1024];

    //private static GCRootDemo t2; 方法区中的类静态属性的引用
    //private static final GCRootDemo t3 = new GCRootDemo(); 方法区中常量引用的对象

    public static void m1(){ //方法在栈中
    GCRootDemo t1 = new GCRootDemo();
    System.gc();
        System.out.println("第一次GC完成");
    }

    public static void main(String[] args) {
        m1();
    }
}
