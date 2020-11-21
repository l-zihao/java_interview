package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/26 16:15
 */
public class arrayListProxy {

    //写一个ArrayList的动态代理类
    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();
        List<String> proxyInstance = (List<String>) Proxy.newProxyInstance(list.getClass().getClassLoader(), list.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return method.invoke(list, args);
            }
        });

        proxyInstance.add("abc");
        proxyInstance.add("cba");
        System.out.println(list);

    }
}
