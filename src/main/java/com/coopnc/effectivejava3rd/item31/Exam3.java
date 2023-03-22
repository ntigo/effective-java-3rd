package com.coopnc.effectivejava3rd.item31;

import java.util.List;

public class Exam3 {

    public static <E> void swap(List<?> list, int i, int j) {
        swapHelper(list, i, j);
    }

    private static <E> void swapHelper(List<E> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }
}
