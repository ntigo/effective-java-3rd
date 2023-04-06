package com.coopnc.effectivejava3rd.item33;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <pre>
 * 타입 안전 이종 컨테이너를 구현한 클래스
 * 비한정적 타입 토큰을 사용
 * </pre>
 *
 * @since 2023. 04. 05
 */
public class Favorites {
    /**
     * 타입 토큰을 키로 가지는 맵 인스턴스
     */
    private Map<Class<?>, Object> favorites = new HashMap<>();

    /**
     * <pre>
     * 타입 토큰을 키로 인스턴스를 추가한다
     * </pre>
     *
     * @param type     타입 토큰
     * @param instance 인스턴스
     * @param <T>      제네릭 매개변수
     *
     * @since 2023. 04. 05
     */
    public <T> void putFavorites(Class<T> type, T instance) {
        /**
         * 로타입 클래스 사용으로 인한 타입 불안전성을 제거하고
         * Class.cast 메서드를 통해 런타임 타입 안전성을 제공
         * (API 수준에서 동적 형변환)
         */
        favorites.put(Objects.requireNonNull(type), type.cast(instance));
    }

    /**
     * <pre>
     * 타입 토큰으로 맵에 등록된 인스턴스를 조회한다
     * </pre>
     *
     * @param type 타입 토큰
     * @param <T>  제네릭 매개변수
     *
     * @return 타입 토큰의 타입 매개변수와 동일한 타입<T>의 인스턴스를 반환한다
     *
     * @since 2023. 04. 05
     */
    public <T> T getFavorites(Class<T> type) {
        return type.cast(favorites.get(type));
    }
}





/*
 private Map<Class<?>, Object> favorites = new HashMap<>();
 favorites.put(Objects.requireNonNull(type), type.cast(instance));
 return type.cast(favorites.get(type));
*/
