package com.dj.mall.auth.provider.role;

import java.util.*;

public class test_1 {

    public Set<Integer> findSameNum(Integer[] args){

        Set<Integer> set = new HashSet();
        if (null==args||args.length==0){
            return set;
        }
        for (Integer n:args) {
            Integer ordNum = n>0?n:-n;
            if (args[ordNum-1]<0){
                set.add(ordNum);
            }else {
                args[ordNum-1] *=-1;
            }
        }
        System.out.println(set);
        return set;
    }
}
