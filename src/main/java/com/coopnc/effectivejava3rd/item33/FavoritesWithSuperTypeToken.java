package com.coopnc.effectivejava3rd.item33;

import com.coopnc.effectivejava3rd.TypeRef;

import java.util.*;

/**
 * <pre>
 * 슈퍼 타입 토큰을 사용한 Favorites 클래스
 * 슈퍼 타입 토큰에 대해서 알아본다
 * </pre>
 *
 * @since 2023. 04. 05
 */
public class FavoritesWithSuperTypeToken {
    /**
     * 슈퍼 타입 토큰을 키로 가지는 맵 인스턴스
     */
    private Map<TypeRef<?>, Object> favorites = new HashMap<>();

    /**
     * <pre>
     * 슈퍼 타입 토큰을 키로 인스턴스를 추가한다
     * </pre>
     *
     * @param type     슈퍼 타입 토큰
     * @param instance 인스턴스
     * @param <T>      제네릭 매개변수
     *
     * @since 2023. 04. 05
     */
    public <T> void putFavorites(TypeRef<T> type, T instance) {
        favorites.put(Objects.requireNonNull(type), instance);
    }

    /**
     * <pre>
     * 슈퍼 타입 토큰으로 맵에 등록된 인스턴스를 조회한다
     * </pre>
     *
     * @param type 슈퍼 타입 토큰
     * @param <T>  제네릭 매개변수
     *
     * @return 타입 토큰의 타입 매개변수와 동일한 타입<T>의 인스턴스를 반환한다
     *
     * @since 2023. 04. 05
     */
    @SuppressWarnings("unchecked")
    public <T> T getFavorites(TypeRef<T> type) {
        return (T) favorites.get(type);
    }

    /**
     * <pre>
     * 슈퍼 타입 토큰의 한계를 보여주는 메서드
     * 제네릭 메서드의 타입 매개변수의 특징으로 인해 힙 오염(타입 불일치) 발생 가능
     * </pre>
     *
     * @param <T> 제네릭 매개변수
     *
     * @return List<T> 형태 값 반환
     *
     * @since 2023. 04. 05
     */
    public <T> List<T> favoriteList() {
        // 제네릭 타입의 List 슈퍼 타입 토큰 생성
        TypeRef<List<T>> ref = new TypeRef<List<T>>() {
        };
        /*
         컨테이너 조회 후 미존재시
         결과 값에 ArrayList 인스턴스 할당 및 컨테이너에 정보 등록
        */
        @SuppressWarnings("unchecked")
        List<T> result = (List<T>) favorites.get(ref);
        if (result == null) {
            result = new ArrayList<>();
            favorites.put(ref, result);
        }
        // 제네릭 타입의 List 반환
        return result;
    }
}
