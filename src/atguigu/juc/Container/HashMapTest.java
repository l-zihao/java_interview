package atguigu.juc.Container;

import java.util.*;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/25 15:37
 */
class User{
    private int age;
    private String name;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


public class HashMapTest {

    public static void main(String[] args) {


        HashMap<Integer, User> map = new HashMap<>();
        map.put(21, new User(21, "张三"));
        map.put(17, new User(17, "李四"));
        map.put(25, new User(25, "王五"));

        HashMap<Integer, User> sortHashMap = hashMapSort(map);

        System.out.println(sortHashMap);

//        ConcurrentHashMap
//        LinkedHashMap
//        Collections.synchronizedMap()

    }

    private static HashMap<Integer, User> hashMapSort(HashMap<Integer, User> map) {
        Set<Map.Entry<Integer, User>> entries = map.entrySet();

        ArrayList<Map.Entry<Integer, User>> list = new ArrayList<>(entries);
        Collections.sort(list, new Comparator<Map.Entry<Integer, User>>() {
            @Override
            public int compare(Map.Entry<Integer, User> o1, Map.Entry<Integer, User> o2) {
                return o2.getValue().getAge() - o1.getValue().getAge();
            }
        });

        LinkedHashMap<Integer, User> linkedHashMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, User> entry : list) {
            linkedHashMap.put(entry.getKey(), entry.getValue());
        }
        return linkedHashMap;
    }

}
