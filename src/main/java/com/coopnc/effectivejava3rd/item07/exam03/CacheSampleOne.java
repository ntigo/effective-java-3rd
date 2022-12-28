package com.coopnc.effectivejava3rd.item07.exam03;

import java.util.HashMap;

public class CacheSampleOne {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();

        Integer key1 = 1000;
        Integer key2 = 2000;

        map.put(key1, "key1의 객체입니다.");
        map.put(key2, "key2의 객체입니다.");

        key1 = null;

        System.gc();  //강제 Garbage Collection

        for (Integer key : map.keySet()) {
            System.out.println(map.get(key));
        }
    }
}
