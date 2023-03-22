package com.coopnc.effectivejava3rd.item31;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;

class Exam2 {

    public static <E extends Comparable<E>> E max(List<E> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List must not be null or empty");
        }

        E max = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            E current = list.get(i);
            if (current.compareTo(max) > 0) {
                max = current;
            }
        }
        return max;
    }

    public static void main(String[] arg) {
        List<ScheduledFuture<?>> scheduledFutures = new ArrayList<>();

        max(scheduledFutures);
    }

}