package com.coopnc.effectivejava3rd.item15.exam2;

public class Main {
    public static void main(String[] args) {
        Exam.VALUES_A[0] = "아무개";

        System.out.println("VALUES_A -----------");
        for(String v : Exam.VALUES_A) {
            System.out.println(v);
        }

//        System.out.println("VALUES_B -----------");
//
//        Exam.VALUES_B.add("아무개");
//        for(String v : Exam.VALUES_B) {
//            System.out.println(v);
//        }

        System.out.println("VALUES_C -----------");
        System.out.println("clone -----------");
        String[] VALUES_C = Exam.getValues();
        VALUES_C[0] = "아무개";
        for(String v : VALUES_C) {
            System.out.println(v);
        }

        System.out.println("original -----------");
        VALUES_C = Exam.getValues();
        for(String v : VALUES_C) {
            System.out.println(v);
        }
    }
}
