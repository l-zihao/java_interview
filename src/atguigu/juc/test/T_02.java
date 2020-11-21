package atguigu.juc.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/21 23:05
 */


public class T_02 {

    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();
    private int number=1;

    void print5(){
        lock.lock();
        try{
            while(number != 1){
                c1.await();
            }

            for (int i = 0; i < 5; i++) {
                System.out.print(number + " ");
            }
            System.out.println();

            number=2;

            c2.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }


    void print10(){
        lock.lock();
        try{
            while(number != 2){
                c2.await();
            }

            for (int i = 0; i < 10; i++) {
                System.out.print(number + " ");
            }
            System.out.println();

            number=3;

            c3.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }


    void print15(){
        lock.lock();
        try{
            while(number != 3){
                c3.await();
            }

            for (int i = 0; i < 15; i++) {
                System.out.print(number + " ");
            }
            System.out.println();

            number=1;

            c1.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        T_02 t_02 = new T_02();

        for(int i=1; i<=10; i++){
            new Thread(()->{
                t_02.print5();
            }, String.valueOf(i)).start();
        }

        for(int i=1; i<=10; i++){
            new Thread(()->{
                t_02.print10();
            }, String.valueOf(i)).start();
        }


        for(int i=1; i<=10; i++){
            new Thread(()->{
                t_02.print15();
            }, String.valueOf(i)).start();
        }
    }
}
