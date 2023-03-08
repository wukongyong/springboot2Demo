package wkyyzl.cn;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        final ArrayList<String> list = new ArrayList<>();
        list.add("123");
        list.add("233");
        System.out.println(list);

        list.add("333");
        System.out.println(list);

        System.out.println(new Date().getTime());

        Integer[] strArray = {1, 3};
        ArrayList list1 = new ArrayList(Arrays.asList(strArray)) ;
        list1.add("1");
        System.out.println(list1);
    }


}
