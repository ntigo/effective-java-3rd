package com.coopnc.effectivejava3rd.item28;

import java.util.Arrays;
import java.util.List;

/**
 * @SafeVarargs 어노테이션 예제 코드를 포함한 클래스
 * @since 2023. 03. 07
 */
public class SafeVarargsExam {

    private SafeVarargsExam() {
    }

    /**
     * <pre>
     * 제네릭 타입의 가변인수를 입력받아 List 타입으로 변환한다.
     * </pre>
     *
     * @param element 요소
     * @param <T>     매개변수 타입
     * @return 입력받은 가변인수를 List 형태로 변환 후 반환한다.
     * @since 2023. 03. 07
     */
    @SafeVarargs
    public static <T> List<T> toList(T... element) {
        // 가변인수 배열의 empty 여부 확인
        return (element != null && element.length != 0) ? Arrays.asList(element) : null;
    }
}
