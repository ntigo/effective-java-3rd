package com.coopnc.effectivejava3rd.item29.exam1;


import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITiNAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITiNAL_CAPACITY];
    }

    public  Object push(Object item) {
        ensureCapacity();
        elements[size++]= item;
        return item;
    }

    public Object pop() {
        if (size == 0) {
            throw  new EmptyStackException();
        }

        Object result = elements[--size];
        elements[size] = null; // 다 쓴 참조 해제
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (elements.length == 0) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
