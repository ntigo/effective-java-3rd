package com.coopnc.effectivejava3rd;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * <pre>
 * 슈퍼 타입 토큰 구현한 클래스
 * 추상화 클래스로 상속을 통해 사용해야 한다
 * </pre>
 * 
 * @param <T> 타입 매개 변수
 *
 * @since 2023. 04. 05
 */
public abstract class TypeRef<T> {
    /**
     * 클래스 정보와 함께 제네릭 정보를 담고 있는 필드 변수
     */
    private final Type type;

    /**
     * <pre>
     * TypeRef 클래스의 protected 생성자로
     * TypeRef 로타입(Raw type) 확인 및
     * 타입 매개변수 로타입(Raw type) 확인,
     * (타입 매개변수 타입(TypeVariable) 확인 기능)을 수행한다
     * </pre>
     */
    protected TypeRef() {
        /**
         * TypeRef 로타입(Raw type) 확인
         */
        ParameterizedType superclass = ParameterizedType.class.cast( getClass().getGenericSuperclass() );
        type = superclass.getActualTypeArguments()[0];
        /**
         * 타입 매개변수 로타입(Raw type) 확인
         */
        if (type instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        /**
         * 타입 매개변수 타입(TypeVariable) 확인
         */
        ParameterizedType pType = ParameterizedType.class.cast(type);
        if (pType.getActualTypeArguments()[0] instanceof TypeVariable) {
            throw new RuntimeException("Couldn't create type token of TypeVariable.");
        }
    }

    /**
     * <pre>
     * HashMap 클래스 사용을 위해 재정의한 equals 메서드
     * </pre>
     *
     * @param o 비교 대상(TypeRef 인스턴스)
     * @return 비교 대상과의 일치 여부를 boolean 형태로 반환
     *
     * @since 2023. 04. 05
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof TypeRef &&
                ((TypeRef) o).type.equals(type);
    }

    /**
     * <pre>
     * HashMap 클래스 사용을 위해 재정의한 hashCode 메서드
     * </pre>
     *
     * @return hash code 값을 int 형태로 반환
     *
     * @since 2023. 04. 05
     */
    @Override
    public int hashCode() {
        return type.hashCode();
    }
}
