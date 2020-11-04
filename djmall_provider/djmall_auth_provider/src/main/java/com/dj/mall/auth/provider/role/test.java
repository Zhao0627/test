package com.dj.mall.auth.provider.role;

import java.util.*;

public class test {
    public static void main(String[] args) {
        Integer orderIds[] = {1,2,3,4,5,6};
        Integer newIds[] = {4,5,6,7,8,0,9};

        List list1 = Arrays.asList(orderIds);
        List list2 = Arrays.asList(newIds);

/*        list1.removeAll(list2);
        System.out.println(list1);
        List list3 = Arrays.asList(orderIds);
        list3.removeAll(list1);
        System.out.println(list3);*/

        Set set1 = new HashSet(list1);
        Set set2 = new HashSet(list2);

        set1 .removeAll(set2);//set1 : a
        System.out.println(set1+"---------"+set2);

        Set set3 = new HashSet(list1);
        set3.removeAll(set1);
        System.out.println("-------相同的值-------"+set3);
        set2 .removeAll(set3);//set2 : d
        System.out.println(set2+"----------"+set3);

        Object[] objects = set1.toArray(new Object[0]);
        for (int i=0; i< objects.length; i++) {
            System.out.println(objects[i].toString());
        }
        List<Integer> integer = new ArrayList<>(set1);
        for (Integer inte :integer) {
            System.out.println(inte);
        }
    }
}
