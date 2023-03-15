package wkyyzl.cn.util;

public class Counter {
    private int num = 0;

    public Counter() {
        num = num + 1;
    }

    public int getNum() {
        return num;
    }

    public void add() {
        num = num + 1;
    }

    public void sub() {
        num = num - 1;
    }
}
