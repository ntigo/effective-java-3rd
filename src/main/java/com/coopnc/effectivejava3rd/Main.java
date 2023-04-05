package com.coopnc.effectivejava3rd;

import com.coopnc.effectivejava3rd.item33.Favorites;
import com.coopnc.effectivejava3rd.item33.FavoritesWithBoundedTypeToken;
import com.coopnc.effectivejava3rd.item33.FavoritesWithSuperTypeToken;

import java.util.Arrays;
import java.util.List;

public class Main {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        /**
         * favorites 클래스 기능을 사용하여 여러 타입의 데이터 추가·조회
         */
        Favorites favorites = new Favorites();
        favorites.putFavorites(Integer.class, 123);
        int integer = favorites.getFavorites(Integer.class);
        favorites.putFavorites(String.class, "가나다");
        System.out.println("Integer.class : " + integer);
        System.out.println("String.class : " + favorites.getFavorites(String.class));

        /**
         * favorites 클래스에 한정적 타입 토큰 적용한 버전
         * Number의 하위 타입만 추가·조회 가능
         */
//        FavoritesWithBoundedTypeToken favoritesWithBoundedTypeToken = new FavoritesWithBoundedTypeToken();
//        favoritesWithBoundedTypeToken.putFavorites(Integer.class, 1);
//        favoritesWithBoundedTypeToken.putFavorites(Double.class, 1.3);
//        favoritesWithBoundedTypeToken.putFavorites(String.class, "1.3");
//        System.out.println("Integer.class : " + favoritesWithBoundedTypeToken.getFavorites(Integer.class));
//        System.out.println("Double.class : " + favoritesWithBoundedTypeToken.getFavorites(Double.class));

        /**
         * 와일드카드 타입의 타입 토큰을 타입 안전하게 사용하기
         * (클라이언트 수준에서 동적 형변환)
         */
//        Class<?> clazz = Integer.class;
//        Number number = favoritesWithBoundedTypeToken.getFavorites(clazz.asSubclass(Number.class));
//        System.out.println("number : " + number);

        /**
         * 제네릭 타입의 타입 토큰
         * 매개변수 타입이 실체화 불가하다
         */
//        Class<List<String>> stringListClass1 = List<String>.class;
//        Class<List<String>> stringListClass2 = List.class;
//        Class<List> stringListClass3 = List.class;
//        Favorites favorites1 = new Favorites();
//        favorites1.putFavorites(List.class, Arrays.asList("가", "나", "다"));
//        favorites1.putFavorites(List.class, Arrays.asList(1, 2, 3));
//        System.out.println("List.class : " + favorites1.getFavorites(List.class));

        /**
         * 슈퍼 타입 토큰의 특징
         */
//        new TypeRef() {};
//        new TypeRef<List>() {};
        
        /**
         * 슈퍼 타입 토큰을 사용하여
         * 제네릭 타입의 타입 안정 이종 컨테이너 만들기
         */
//        FavoritesWithSuperTypeToken favoritesWithSuperTypeToken = new FavoritesWithSuperTypeToken();
//        favoritesWithSuperTypeToken.putFavorites(new TypeRef<List<String>>() {
//        }, Arrays.asList("가", "나", "다"));
//        favoritesWithSuperTypeToken.putFavorites(new TypeRef<List<Integer>>() {
//        }, Arrays.asList(1, 2, 3));
//        System.out.println("List<String> : " + favoritesWithSuperTypeToken.getFavorites(new TypeRef<List<String>>() {
//        }));
//        System.out.println("List<Integer> : " + favoritesWithSuperTypeToken.getFavorites(new TypeRef<List<Integer>>() {
//        }));

        /**
         * 제네릭 메서드의 타입 매개변수의 특징으로 인해 힙 오염(타입 불일치) 발생
         */
//        FavoritesWithSuperTypeToken favoritesWithSuperTypeToken1 = new FavoritesWithSuperTypeToken();
//        List<Integer> integerList = favoritesWithSuperTypeToken1.favoriteList();
//        List<String> stringList = favoritesWithSuperTypeToken1.favoriteList();
//        stringList.add("string");
//        integerList.add(1);
//        for( String s : stringList ) System.out.println("String s : " + s);
    }
}
