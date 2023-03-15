package com.coopnc.effectivejava3rd.item30;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) {
        Set<String> s1 = Set.of("A", "B", "C", "D");
        Set<String> s2 = Set.of("E", "F", "G");
        Set<Integer> s3 = Set.of(1, 2, 3, 4);
        Set<RawType> s4 = Set.of(new RawType(), new RawType());

        System.out.println(RawType.union(s1, s2));
        System.out.println(RawType.union(s1, s3));
        System.out.println(RawType.union(s1, s4));

//        System.out.println(StringType.union(s1, s2));
//        System.out.println(StringType.union(s1, s3));
//        System.out.println(StringType.union(s1, s4));

//        System.out.println(GenericType.union(s1, s2));
//        System.out.println(GenericType.union(s1, s3));
//        System.out.println(GenericType.union(s1, s4));

//        Sample.<Integer>printer().print(1);
//        Sample.<String>printer().print("");

//        String[] stringArr = {"A", "B", "C"};
//        UnaryOperator<String> u1 = IdentityFunction.identityFunction();
//        for (String s : stringArr) {
//            System.out.println(u1.apply(s));
//        }
//
//        int[] intArr = {1,2,3,4};
//        UnaryOperator<Integer> u2 = IdentityFunction.identityFunction();
//        for (int i : intArr) {
//            System.out.println(u2.apply(i));
//        }

//        List<String> list1 = Arrays.asList("1", "2");
//        System.out.println(RecursiveTypeBound.max(list1));
//
//        List<ComparableClass> list2 = Arrays.asList(new ComparableClass(1), new ComparableClass(2));
//        System.out.println(RecursiveTypeBound.max(list2));
    }
}
