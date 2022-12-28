package com.coopnc.effectivejava3rd.item07.exam03;

import java.util.WeakHashMap;

public class CacheSampleTwo {
    public static void main(String[] args) {
        WeakHashMap<Integer, String> map = new WeakHashMap<>();

        Integer key1 = 1000;
        Integer key2 = 2000;

        map.put(key1, "key1의 객체입니다.");
        map.put(key2, "key2의 객체입니다.");

        key1 = null;

        //결과로 key2만 찍힌다.
        System.out.println("gc before -----------------------");
        for (Integer key : map.keySet()) {
            System.out.println(map.get(key));
        }

        System.gc();  //강제 Garbage Collection

        System.out.println();
        System.out.println("gc after -----------------------");
        //결과로 key2만 찍힌다.
        for (Integer key : map.keySet()) {
            System.out.println(map.get(key));
        }
    }
}
