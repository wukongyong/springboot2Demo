package wkyyzl.cn.design.proxy.jdk_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TrainStationProxyFactory {

    //声明目标对象
    private TrainStation trainStation = new TrainStation();

    public SellTickets getProxyObject() {
        SellTickets proxyObject = (SellTickets) Proxy.newProxyInstance(
                trainStation.getClass().getClassLoader(),
                trainStation.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        System.out.println("invoke方法执行了");
                        //反射
                        method.invoke(trainStation, args);
                        return null;

                    }
                }
        );
        return proxyObject;
    }

}
