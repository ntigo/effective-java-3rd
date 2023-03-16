package com.coopnc.effectivejava3rd.item30;

import java.util.HashSet;
import java.util.Set;

public class StringType {
    // javac StringType.java -Xlint:unchecked
    public static Set<String> union(Set<String> s1, Set<String> s2) {
        Set<String> result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    }
}
