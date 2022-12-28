package com.coopnc.effectivejava3rd.item07.exam01;

import java.util.Arrays;
import java.util.EmptyStackException;

class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
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

    // 테스트를 위해 추가된 인덱스로 데이터를 조회하는 메소드
    public Object get(int index) {
        return elements[index];
    }
}

public class SampleOne{
    public static void main(String[] args) {
        Stack stack = new Stack();

        for (int i = 0; i < 5; ++i) {
            stack.push(new Object());
        }

        System.out.println("pop-------------------------");
        for (int i = 0; i < 5; ++i) {
            System.out.println(stack.pop());
        }

        System.out.println();
        System.out.println("get-------------------------");
        for (int i = 0; i < 5; ++i) {
            System.out.println(stack.get(i));
        }
    }
}
