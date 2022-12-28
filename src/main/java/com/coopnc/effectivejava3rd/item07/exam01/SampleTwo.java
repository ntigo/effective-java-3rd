package com.coopnc.effectivejava3rd.item07.exam01;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.HashMap;

public class SampleTwo {
    private Element[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public SampleTwo() {
        elements = new Element[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Element e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Element pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }

        return elements[--size];
    }

    public void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}

class Element {
    private int i;
    private String str;
    private boolean b;
    private HashMap<String, SubElement> map = new HashMap<>();
}

class SubElement {
    private HashMap<String, String> map = new HashMap<>();
}
