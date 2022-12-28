package com.coopnc.effectivejava3rd.item07.exam02;

import java.util.Arrays;
import java.util.EmptyStackException;

class ImprovementStack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public ImprovementStack() {
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

        Object result = elements[--size];
        elements[size] = null; // 다 쓴 참조 해제, 해당 참조를 다 썻을 때 null 처리

        return result;
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

public class SampleOne {
    public static void main(String[] args) {
        ImprovementStack stack = new ImprovementStack();

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