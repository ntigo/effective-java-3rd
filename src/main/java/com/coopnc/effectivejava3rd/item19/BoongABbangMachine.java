package com.coopnc.effectivejava3rd.item19;

// 훅(Hook)을 설명하기 위해 만든 클래스
/**
 * 붕어빵 기계를 추상화한 클래스
 */
public class BoongABbangMachine {

    /**
     * 요청한 개수만큼 붕어빵을 만들어낸다.
     *
     * @param count 상품 번호
     *
     * @implSpec    붕어빵을 만들기 위해 붕어빵 개수를 입력받는다.
     *              입력받은 개수가 0보다 작거나 같을 경우 AssertionError를 발생시킨다.
     *              insertPuree 메서드를 재정의함으로써 붕어빵 종류를 변경할 수 있다.
     *
     * @see         BoongABbangMachine#insertPuree()
     *
     * @since       2023. 02. 08
     */
    public void make(int count) {
        System.out.println("화로에 불을 붙입니다.");
        System.out.println("밀가루 반죽을 틀에 붓습니다.");
        insertPuree();
        System.out.println("붕어빵이 익을 때까지 기다립니다.");
        System.out.println("붕어빵이 " + count + "개 완성되었습니다.");
    }

    /**
     * 붕어빵에 들어가는 고물을 결정한다.
     *
     * @implSpec    make 메서드 내부에서 사용되며,
     *              메서드 재정의시 붕어빵 종류를 변경할 수 있다.
     *
     * @since       2023. 02. 08
     */
    protected void insertPuree() {
        System.out.println("팥 고물을 넣습니다.");
    }
}
