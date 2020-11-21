package Singleton;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/9 23:31
 */
public class Singleton6 {

    private Singleton6(){}

    public static Singleton6 getInstance(){
        return inner.instance;
    }

    private static class inner{
        private static final Singleton6 instance = new Singleton6();
    }

    public static void main(String[] args) {
        Singleton6 s1 = Singleton6.getInstance();
        Singleton6 s2 = Singleton6.getInstance();
        System.out.println(s1 == s2);
    }
}
