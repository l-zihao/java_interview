package initalize;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/5/9 23:39
 */
public class Father {

    private int a = a();

    private static int b = b();

    Father(){
        System.out.print("(5)");
    }

    {
        System.out.print("(3)");
    }

    static {
        System.out.print("(4)");
    }

    public int a(){
        System.out.print("(1)");
        return 1;
    }

    public static int b(){
        System.out.print("(2)");
        return 2;
    }
}
