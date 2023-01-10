package com.coopnc.effectivejava3rd.item11.exam01;

import java.util.HashMap;
import java.util.Map;

public class exam01 {
    public static void main( String[] args ) {
        doTest();
    }
    
    public static void doTest() {
        Map<PhoneNumber,String> m = new HashMap<>();
        
        PhoneNumber phoneNumber =  new PhoneNumber(707,867,5309);
        
        System.out.println(phoneNumber.hashCode());

        m.put(phoneNumber, "Jenny");

        PhoneNumber phoneNumber2 = new PhoneNumber(707,867,5309);

        System.out.println("hashCode: " + phoneNumber2.hashCode());


        System.out.println(m.get(phoneNumber2));
    }
}
