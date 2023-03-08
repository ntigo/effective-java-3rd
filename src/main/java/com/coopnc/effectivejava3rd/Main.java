package com.coopnc.effectivejava3rd;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main( String[] args ) {
        // 실체화, 소거
        // \Java\Workspace\effective-java-3rd\src\main\java\com\coopnc\effectivejava3rd
        // javac Main.java -encoding UTF-8
        String[] stringArray = new String[] { "실체화 타입" };
        List<String> stringList = Arrays.asList( "실체화 불가 타입" );

        // 공변
//        Object[] objectArray = new Long[1];
//        objectArray[0] = "타입이 달라 넣을 수 없다.";

        // 불공변
//        List<Object> ol = new ArrayList<Long>();
//        ol.add("타입이 달라 넣을 수 없다.");

        // 제네릭 배열
//        GenericArray<List<String>> genericArray = new GenericArray<>(10);
//        List<String> strList = Arrays.asList( "제네릭 배열 테스트입니다." );
//        genericArray.set(0, strList);
//        System.out.println(genericArray.get(0));

        // @SafeVarags
//        System.out.println(SafeVarargsExam.toList("제네릭", "소거", "불공변", "실체화 불가"));

        // 제네릭 배열 사고 실험
//        List<String>[] stringLists = new List<String>[1];
//        List<Integer> intList = Arrays.asList(42);
//        Object[] objects = stringLists;
//        objects[0] = intList;
//        String s = stringLists[0].get(0);

        // 제네릭 배열 생성
//        GenericArray<List<String>> stringListsWrapper = new GenericArray<>(1);
//        List<Integer> intList = Arrays.asList(42);
//        // 배열의 공변성으로 할당 성공
//        Object[] objects = stringListsWrapper.getArray();
//        // 제네릭의 소거 메커니즘으로 할당 성공
//        objects[0] = intList;
//        // ClassCastException 발생
//        String s = stringListsWrapper.get(0).get(0);
    }
}
