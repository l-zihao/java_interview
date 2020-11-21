package initalize;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/9 23:43
 */
public class Son extends Father {

    private int a = a();

    private static int b = b();

    Son(){
        System.out.print("(10)");
    }

    {
        System.out.print("(9)");
    }

    static {
        System.out.print("(8)");
    }

    public int a(){
        System.out.print("(6)");
        return 1;
    }

    public static int b(){
        System.out.print("(7)");
        return 2;
    }

    public static void main(String[] args) {
        Son s1 = new Son();
        Son s2 = new Son();
    }

}
