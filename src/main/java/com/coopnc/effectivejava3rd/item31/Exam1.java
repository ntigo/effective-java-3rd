package com.coopnc.effectivejava3rd.item31;

import java.util.*;

public class Exam1 {

    public static <E> Set<E> union(Set<E> s1, Set<E> s2) {
        Set<E> result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    }

    public static void main(String[] arg) {
        Set<Integer> integers1 = Set.of(1,3,5);
        Set<Double> doubleSet = Set.of(1.0,2.0);
       // Set<Number> numbers = Exam1.union(integers1,doubleSet);
    }
}
