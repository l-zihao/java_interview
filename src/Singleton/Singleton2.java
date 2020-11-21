package Singleton;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/9 23:04
 */
public enum  Singleton2 {

    INSTANCE

}

class test{
    public static void main(String[] args) {
        Singleton2 s1 = Singleton2.INSTANCE;
        Singleton2 s2 = Singleton2.INSTANCE;
        System.out.println(s1 == s2);
    }
}