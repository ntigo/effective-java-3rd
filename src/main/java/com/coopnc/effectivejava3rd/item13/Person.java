package com.coopnc.effectivejava3rd.item13;

public class Person implements Cloneable {
    private String name;
    private int age;

    public Person( final String name, final int age ) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public Person clone() throws RuntimeException {
        try {
            return (Person)super.clone();
        } catch( CloneNotSupportedException e ) {
            throw new RuntimeException();
        }
    }
}
