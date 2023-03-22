package wkyyzl.cn;

import wkyyzl.cn.util.Counter;

public class Test {

    public static void main(String[] args) {
        Counter counter = new Counter();
        Counter level = new Counter();
        System.out.println("level: " + level.getNum());
        Test.loopTest(1, counter, level);
    }

    public static void loopTest(int num, Counter counter, Counter level) {
        if (num == 4) {
            level.sub();
            return;
        }
        level.add();
        //System.out.println(counter.getNum());
        counter.add();
        for (int i = 1; i < 3; i++) {
            //System.out.println("level: " + level.getNum() + " num: " + i);
            //System.out.println("num:" + (num + 1) + "counter:" + counter.getNum() + "level:" + level.getNum());
            System.out.println("level: " + (num + 1) + " num: " + i);
            loopTest(num + 1, counter, level);
        }
    }

}
