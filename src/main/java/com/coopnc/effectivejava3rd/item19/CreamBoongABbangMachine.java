package com.coopnc.effectivejava3rd.item19;

/**
 * 크림 붕어빵을 만드는 기계
 */
public class CreamBoongABbangMachine extends BoongABbangMachine {

    /**
     * 팥 고물대신 슈크림을 넣는다.
     *
     * @implSpec    make 메서드 내부에서 사용되며,
     *              메서드 재정의시 붕어빵 종류를 변경할 수 있다.
     *
     * @since       2023. 02. 08
     */
    @Override
    protected void insertPuree() {
        System.out.println("슈크림을 넣습니다.");
    }

}
