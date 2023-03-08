package com.coopnc.effectivejava3rd.item28;

/**
 * 제네릭 배열을 구현한 클래스
 *
 * @param <E>
 * @since 2023. 03. 07
 */
public class GenericArray<E> {
    // 제네릭 배열
    private final E[] genericArray;

    // 형변환 경고 제거
    @SuppressWarnings("unchecked")
    public GenericArray(int size) {
        // 명시적 형변환을 통해 제네릭 배열로 형변환
        genericArray = (E[]) new Object[size];
    }

    /**
     * 인덱스에 해당하는 요소를 반환한다.
     *
     * @param index 배열의 인덱스
     * @return 제네릭으로 선언된 요소를 반환한다.
     * @since 2023. 03. 07
     */
    public E get(int index) {
        return genericArray[index];
    }

    /**
     * 입력한 인덱스에 요소를 할당한다.
     *
     * @param index   배열의 인덱스
     * @param element 배열에 할당될 요소
     * @since 2023. 03. 07
     */
    public void set(int index, E element) {
        genericArray[index] = element;
    }

    /**
     * 제네릭 배열 사고 실험을 위해 Array 필드의 접근 메서드를 제공
     *
     * @return 매개변수 타입 배열 E[]을 반환한다.
     * @since 2023. 03. 07
     */
    public E[] getArray() {
        return genericArray;
    }
}