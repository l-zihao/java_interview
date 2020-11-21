package atguigu.juc.Container;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/17 11:27
 *
 * 集合类不安全的问题
 *  ArrayList
 */
public class ContainerNotSafeDemo {

    public static void main(String[] args) {
        //listNoSafe();
        //setNoSafe();
        mapNoSafe();

    }

    private static void mapNoSafe() {
        //Map<String, String> map = new HashMap<>();
        //Map<String, String> map = new Hashtable<>();
        Map<String, String> map = new ConcurrentHashMap<>();


        for(int i=1; i<=30; i++){
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    private static void setNoSafe() {
        //Set<String> set = new HashSet<>();
        //Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet<>();

        for(int i=1; i<=30; i++){
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }

        /**
         * 1. HashSet底层的数据结构是什么？
         *    HashMap
         * 2. HashMap是k-v结构，HashSet底层是HashMap，那为什么HashSet的add方法只需要传一个值？
         *    HashSet的add方法将传进去的值作为k-v键值对的key，而键值对的value是一个PRESENT常量
         *    public boolean add(E e) {
         *         return map.put(e, PRESENT)==null;
         *     }
         */
    }

    private static void listNoSafe() {
    /*
    List<String> list= Arrays.asList("a", "b", "c");
    list.forEach(System.out::println);
    */

        //List<String> list = new ArrayList<>();
        //List<String> list = new Vector<>();
        //List<String> list = new Stack<>();
        //List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();


//        list.add("a");
//        list.add("b");
//        list.add("c");
//        for (String s : list) {
//            System.out.println(s);
//        }

        for(int i=1; i<=30; i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
        //java.util.ConcurrentModificationException

        /*
        * 1. 故障现象
        *     java.util.ConcurrentModificationException
        * 2. 导致原因
        *     并发争抢修改导致，参考花名册签名情况
        *     一个人正在写入，另外一个同学过来抢夺，导致数据不一致异常，并发修改异常
        * 3. 解决方案
        *   3.1 new Vector<>()  new Stack<>()
        *   3.2 Collections.synchronizedList(new ArrayList<>())
        *   3.3 new CopyOnWriteArrayList()
        * 4. 优化建议（同样的错误不犯第2次）
        *
        * */
    }
}
