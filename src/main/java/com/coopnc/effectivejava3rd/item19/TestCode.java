package com.coopnc.effectivejava3rd.item19;

public class TestCode {
    public static void main(String[] args) {
		// 훅(Hook) 예제
        BoongABbangMachine boongABbangMachine = new BoongABbangMachine();
        boongABbangMachine.make(1);
		boongABbangMachine = new CreamBoongABbangMachine();
		boongABbangMachine.make(1);
		
		// 생성자 오동작 예제
//  	  Sub sub = new Sub();
//  	  sub.overrideMe();
		
		// private 도우미 메서드 예제
//		  AddInt addInt = new AddInt();
//        System.out.println( "1~10까지 합(55) : " + addInt.addAll( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 ) );
    }
}
