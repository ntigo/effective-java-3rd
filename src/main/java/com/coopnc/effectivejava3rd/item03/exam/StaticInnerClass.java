package com.coopnc.effectivejava3rd.item03.exam;

public class StaticInnerClass {
	private StaticInnerClass(){

	}

	// Holder Idiom
	private static class StaticInnerClassHolder{
		private static final StaticInnerClass instance = new StaticInnerClass();
	}

	public  static StaticInnerClass getInstance(){
		return StaticInnerClassHolder.instance;
	}

	public static void doTest() {

	}

}