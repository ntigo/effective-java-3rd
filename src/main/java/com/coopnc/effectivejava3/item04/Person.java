package com.coopnc.effectivejava3rd.item04.exam02;

public abstract class Person {
    public abstract void print();
}

class Child extends Person {

    @Override
    public void print() {
        System.out.println("테스트");
    }
}

class AbsTest {
    public static void main(String[] args) {
        Child child = new Child();
        child.print();
    }
}