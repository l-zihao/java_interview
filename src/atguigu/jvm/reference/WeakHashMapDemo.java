package atguigu.jvm.reference;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/23 11:21
 */
public class WeakHashMapDemo {

    public static void main(String[] args) {
        myHashMap();
        System.out.println("=====================");
        myWeakHashMap();

        /**
         * {1=HashMap}
         * {1=HashMap}
         * {1=HashMap}	1
         * =====================
         * {2=WeakHashMap}
         * {2=WeakHashMap}
         * {}	0
         */
    }

    private static void myHashMap() {

        HashMap<Integer, String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value = "HashMap";

        map.put(key, value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map + "\t" + map.size());
    }

    private static void myWeakHashMap() {
        WeakHashMap<Integer, String> map = new WeakHashMap<>();
        Integer key = new Integer(2);
        String value = "WeakHashMap";

        map.put(key, value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map + "\t" + map.size());
    }
}
