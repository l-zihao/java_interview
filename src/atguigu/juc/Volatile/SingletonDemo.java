package atguigu.juc.Volatile;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/16 8:05
 */
public class SingletonDemo {

//    private static SingletonDemo instance = null;
    private static volatile SingletonDemo instance = null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName() + " SingletonDemo()构造方法");
    }

    //单线程
    public static SingletonDemo getInstance01(){
        if(instance == null){
            instance = new SingletonDemo();
        }
        return instance;
    }

    //synchronized 修饰静态方法：整个静态方法，作用于所有对象
    public static synchronized SingletonDemo getInstance02(){
        if(instance == null){
            instance = new SingletonDemo();
        }
        return instance;
    }

    //DCL（Double Check Loc） 双端检锁机制
    public static SingletonDemo getInstance03(){
        if(instance == null){
            synchronized (SingletonDemo.class){
                if(instance == null){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());

        for(int i=1; i<=10; i++){
            new Thread(()->{
                SingletonDemo.getInstance03();
            }, String.valueOf(i)).start();
        }
    }
}
