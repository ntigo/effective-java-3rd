package com.coopnc.effectivejava3rd.item31;

import java.util.Collection;

public class Stack<E> {
    public Stack() {

    }

    public void push(E e) {

    }

    public E pop() {
        return null;
    }

    public boolean isEmpty() {
        return false;
    }

    // Java의 모든 컬렉션은 Iterable 인터페이스를 구현합니다.
    public void pushAll(Iterable< ? extends E> src) {
        for (E e : src)
            push(e);
    }

    public void popAll(Collection<? super E> dst) {
        while (!isEmpty()) {
            dst.add(pop());
        }
    }
}