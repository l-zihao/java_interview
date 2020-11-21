package algorithm.synchronized_lock;

import java.util.concurrent.TimeUnit;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/9 11:59
 */
public class Account {

    String name;
    double balance;

    public synchronized void set(String name, double balance){
        this.name = name;

        //暂停一会儿线程
        try{ TimeUnit.SECONDS.sleep(2); }catch (InterruptedException e){ e.printStackTrace(); }

        this.balance = balance;
    }

    public /*synchronized*/ double getBalance(String name){
        return this.balance;
    }

    public static void main(String[] args) {
        Account a = new Account();
        new Thread(() -> {
            a.set("zhangsan", 100.0);
        }).start();
        //暂停一会儿线程
        try{ TimeUnit.SECONDS.sleep(1); }catch (InterruptedException e){ e.printStackTrace(); }

        System.out.println(a.getBalance("zhangsan"));

        //暂停一会儿线程
        try{ TimeUnit.SECONDS.sleep(2); }catch (InterruptedException e){ e.printStackTrace(); }

        System.out.println(a.getBalance("zhangsan"));
    }
}
