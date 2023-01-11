package com.coopnc.effectivejava3rd.item11;

import java.util.HashMap;
import java.util.Map;

public class Exam01 {
    public static void main( String[] args ) {
        doTest();
    }
    
    public static void doTest() {

        // 1. 같은 오브젝트로 map.get()
        // 2. 같은 haseCode 다른 객체
        // 3. haseCode 구현

        Map<PhoneNumber,String> m = new HashMap<>();
        
        PhoneNumber phoneNumber =  new PhoneNumber(707,867,5309);
        
        System.out.println(phoneNumber.hashCode());

        m.put(phoneNumber, "Jenny");

    }
}
