package Singleton;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/9 23:07
 */
public class Singleton3 {

    private static Singleton3 instance;

    private String info;

    private Singleton3(String info){
        this.info = info;
    }

    static {
        try {
            Properties pro = new Properties();
            pro.load(Singleton3.class.getClassLoader().getResourceAsStream("Singleton/singleton.properties"));
            String info = pro.getProperty("info");
            instance = new Singleton3(info);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Singleton3 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        Singleton3 s1 = Singleton3.getInstance();
        Singleton3 s2 = Singleton3.getInstance();
        System.out.println(s1 == s2);
    }

}
