package com.dj.mall.order.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindSameNumber {

    public Set<Integer> findSameNum(List<Integer> list){
        Set<Integer> set = new HashSet();
        for ( int i = 0 ; i < list.size() - 1 ; i ++ ) {
            for ( int j = list.size() - 1 ; j > i; j -- ) {
                if (list.get(j).equals(list.get(i))) {
                    set.add(list.get(j));
                }
            }
        }
        System.out.println(set);
        return set;
    }

    public static void main(String[] args) {
        Set<Integer> set = new HashSet();
        List<Integer> list = new ArrayList<>();
        list.add(21);list.add(21);list.add(21);list.add(22);list.add(23);list.add(25);list.add(23);list.add(27);list.add(35);list.add(27);list.add(27);list.add(27);
        for ( int i = 0 ; i < list.size() - 1 ; i ++ ) {
            for ( int j = list.size() - 1 ; j > i; j -- ) {
                if (list.get(j).equals(list.get(i))) {
                    set.add(list.get(j));
                }
            }
        }
        System.out.println(set);
    }

}
