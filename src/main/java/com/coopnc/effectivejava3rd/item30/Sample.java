package com.coopnc.effectivejava3rd.item30;

import java.util.Collections;
import java.util.Comparator;

public class Sample {
    @SuppressWarnings("unchecked")
    public static <T> Printable<T> printer() {
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