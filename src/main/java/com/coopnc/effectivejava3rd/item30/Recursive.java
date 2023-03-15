package com.coopnc.effectivejava3rd.item30;

import java.util.Collection;

public class Recursive {
    public static <E extends Comparable<E>> E max (Collection<E> c) {
        if (c.isEmpty())
            throw new IllegalArgumentException();

        E result = null;
        for (E e : c) {
            if (result == null || e.compareTo(result) > 0) {
                result = e;
            }
        }

        return result;
    }
}
