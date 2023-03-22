package wkyyzl.cn.design.proxy.jdk_proxy;

public class TestClient {
    public static void main(String[] args) {
        TrainStationProxyFactory trainStationProxy = new TrainStationProxyFactory();
        SellTickets proxyObject = trainStationProxy.getProxyObject();
        proxyObject.sell();
        proxyObject.test("string", 19);

        System.out.println(proxyObject.getClass());

        while (true) {
        }
    }
}
