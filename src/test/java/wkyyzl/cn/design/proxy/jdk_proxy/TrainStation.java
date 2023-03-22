package wkyyzl.cn.design.proxy.jdk_proxy;

public class TrainStation implements SellTickets {
    @Override
    public void sell() {
        System.out.println("火车站卖票");
    }

    @Override
    public String test(String s, int i) {
        System.out.println(s + i);
        return s + i;
    }
}
