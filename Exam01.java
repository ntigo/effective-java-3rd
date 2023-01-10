package com.coopnc.effectivejava3rd.item11;

import java.util.HashMap;
import java.util.Map;

public class Exam01 {

    public static void doTest() {

        Map<PhoneNumber,String> hm = new HashMap<>();
        PhoneNumber phoneNumber =  new PhoneNumber(707,867,5309);
        System.out.println(phoneNumber.hashCode()); 
        hm.put(phoneNumber, "Jenny");
     
        phoneNumber phoneNumber2 = new PhoneNumber(707, 867, 123);
        System.out.println(phoneNumber2.hashCode());
        System.out.println(hm.get(new PhoneNumber(707, 867, 5309)));

    }
}
