package com.coopnc.effectivejava3rd.item01.exam05;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ComparableTest {
    public static void doTest() {
        List<Integer> numberList = Arrays.asList( 5, 1, 3, 4, 8, 9, 11, 123, 23, 0, 7, 13);
        System.out.println("Unsorted list: " + numberList);

        Comparator<Integer> desc = (o1, o2) -> o2 - o1;
        numberList.sort(desc);
        System.out.println("DESC Sorted list: " + numberList );
        numberList.sort(desc.reversed());
        System.out.println("ASC Sorted list: " + numberList );
    }
}
