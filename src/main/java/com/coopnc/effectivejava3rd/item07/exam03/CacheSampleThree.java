package com.coopnc.effectivejava3rd.item07.exam03;

import java.util.LinkedHashMap;
import java.util.Map;

public class CacheSampleThree {
    public static void main(String[] args) {
        final int MAX_ENTRIES = 5;
        LinkedHashMap<Integer, String> map = new LinkedHashMap(MAX_ENTRIES + 1) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > MAX_ENTRIES;
            }
        };

        map.put(0, "H");
        map.put(1, "E");
        map.put(2, "L");
        map.put(3, "L");
        map.put(4, "O");
        map.put(5,"!");

        System.out.println(map);
    }
}
