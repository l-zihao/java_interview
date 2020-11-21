package atguigu.juc.lock;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/11/2 19:02
 */
public class SyncChild  extends SyncParent{
    @Override
    synchronized void m() {
        System.out.println("SyncChild...start");
        super.m();
        System.out.println("SyncChild...end");
    }

    public static void main(String[] args) {
        new SyncChild().m();
    }
}

class SyncParent{
    synchronized void m(){
        System.out.println("SyncParent");
    }
}