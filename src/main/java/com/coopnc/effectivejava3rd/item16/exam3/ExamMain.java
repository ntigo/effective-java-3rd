package com.coopnc.effectivejava3rd.item16.exam3;

public class ExamMain {
	/*//package-private 접근자로 생성되었기에 같은 패키지 안에서만 값 접근 및 변경이 가능하다
	Exam1 aaa = new Exam1();
	aaa.x

	//private 중첩 클래스임으로 같은 패키지 내에서만 해당 클래스 접근이 가능하면 값 변경 시 생성한 객체를 통해서만 접근 및 변경이 가능
	Exam2 b = new Exam2( 1, 1 );*/
	public void Test() {
		Exam1 aaa = new Exam1();
		aaa.x = 1;
		aaa.y = 2;

		Exam2 bbb = new Exam2( 1, 2 );
//		bbb.changeXY( 2, 1 );


	}

}
