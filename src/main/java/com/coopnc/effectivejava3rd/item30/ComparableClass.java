package com.coopnc.effectivejava3rd.item30;

public class ComparableClass implements Comparable<ComparableClass>{
    public final int val;

    ComparableClass(final int val) {
        this.val = val;
    }

    @Override
    public int compareTo(ComparableClass o) {
        return Integer.compare(this.val, o.val);
    }
}
