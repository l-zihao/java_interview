package Singleton;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/9 19:16
 */
public class Singleton1 {

    private static final Singleton1 singleton1 = new Singleton1();

    private Singleton1(){}

    public static Singleton1 gettInstance(){
        return singleton1;
    }

    public static void main(String[] args) {
        Singleton1 s1 = Singleton1.gettInstance();
        Singleton1 s2 = Singleton1.gettInstance();
        System.out.println(s1 == s2);
    }

}
