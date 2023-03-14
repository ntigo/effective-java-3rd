package com.coopnc.effectivejava3rd.item28;

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <pre>
 * 컬렉션 안의 원소 중 하나를 무작위로 선택해 반환하는
 * choose 메서드를 제공하는 클래스
 * </pre>
 *
 * @since 2023. 03. 07
 */
public class Chooser {
    // 데이터 관리를 담당하는 배열
    private final Object[] choiceArray;

    public Chooser(Collection choices) {
        choiceArray = choices.toArray();
    }

    /**
     * 컬렉션 안의 원소 중 하나를 무작위로 선택해 반환한다.
     *
     * @return 무작위 선택된 원소를 Object 형태로 반환한다.
     * @since 2023. 03. 07
     */
    public Object choose() {
        Random rnd = ThreadLocalRandom.current();
        return choiceArray[rnd.nextInt(choiceArray.length)];
    }
}
