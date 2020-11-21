package atguigu.juc.Atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/16 10:52
 *
 * 1. CAS是什么？ ===> compareAndSet
 *    比较并交换
 */
public class CASDemo {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5, 2019) + " current data: " + atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(5, 2014) + " current data: " + atomicInteger.get());

    }

}
