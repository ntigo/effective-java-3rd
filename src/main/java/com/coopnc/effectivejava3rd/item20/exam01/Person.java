package com.coopnc.effectivejava3rd.item20.exam01;

public class Person implements Comparable<Person> {
    private String Name;
    private Integer Age;
  
    public Person(String name, Integer age) {
      Name = name;
      Age = age;
    }
  
    @Override
    public int compareTo(Person o) {
      if(this.Age > o.Age) return 1;
      else if(this.Age == o.Age) return 0;
      else return -1;
    }
  }