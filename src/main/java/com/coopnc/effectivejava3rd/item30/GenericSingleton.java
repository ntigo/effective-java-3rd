package com.coopnc.effectivejava3rd.item30;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class GenericSingleton {
    @SuppressWarnings("unchecked")
    public static <T> Printable<T> printer() {
//        Comparator<String> c = Collections.reverseOrder();
//        Map<String, String> map = Collections.emptyMap();
        return (Printable<T>) Printer.PRINTER;
    }

    private static class Printer implements Printable<Object> {
        static final Printer PRINTER = new Printer();

        private Printer() {

        }

        public void print(Object obj) {
            System.out.println(obj);
        }
    }
}