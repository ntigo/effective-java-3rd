package com.module2;

//import com.module1.api.Module1Api;
//import com.module1.service.Module1Service;

import java.lang.reflect.Field;

public class Main {

    public static void main(String[] args) {
        //Module1에 속해있는 Module1Api을 가져오기 위해 모듈 디스크립터 정의가 필요하다.
//        Module1Api api = new Module1Api();
//        api.method();
//
//        Module1Service service = new Module1Service();

        try {
            Class<?> clz = Class.forName("Module1Api");
            for(Field field: clz.getFields()) {
                System.out.println(field);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}