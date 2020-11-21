package collection;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/29 9:39
 */
public class MyHashMap<K,V> {

    private Entry[] table;

    private static Integer CAPACITY=8;

    MyHashMap(){
        this.table = new Entry[CAPACITY];
    }

    public Object get(K key){
        int hash = key.hashCode();
        int i = hash % 8;

        Entry<K,V> node = table[i];

        if(node != null){
            for(Entry<K,V> entry=node; entry!=null; entry=entry.next){
                if(entry.key.equals(key)){
                    return entry.value;
                }
            }
        }

        return null;
    }


    public Object put(K key, V value){

        int hash = key.hashCode();
        int i = hash % 8;

        Entry<K,V> node = table[i];

        if(node == null){
            table[i] = new Entry<K,V>(key, value, null, hash);
        }else {

            for(Entry<K,V> entry=node; entry!=null; entry=entry.next){
                if(entry.key.equals(key)){
                    Object oldValue = entry.value;
                    entry.value = value;
                    return oldValue;
                }
            }

            Entry<K,V> newEntry = new Entry<K,V>(key, value, node, hash);
            table[i] = newEntry;
        }

        return null;
    }

    class Entry<K, V>{
        K key;
        V value;
        Entry<K, V> next;
        int hash;

        public Entry(K key, V value, Entry<K, V> next, int hash) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.hash = hash;
        }
    }

    public static void main(String[] args) {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("1", "占山");
        map.put("2", "李四");
        map.put("3", "王五");

        System.out.println(map.get("2"));
    }
}
