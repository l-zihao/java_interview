package atguigu.juc.lock;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/30 16:54
 */
public class synchronizedTest {

    private volatile static int number = 0;

    public synchronized static void add(){
        number++;
    }

    public static void main(String[] args) {

        for(int i=1; i<=10; i++){
            new Thread(()->{
                for (int j = 0; j < 2000; j++) {
                    add();
                }
            }, String.valueOf(i)).start();
        }


        while (Thread.activeCount() > 2){

        }

        System.out.println(Thread.currentThread().getName() + "\t" + number);
    }

}
