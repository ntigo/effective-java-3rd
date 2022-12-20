package com.coopnc.effectivejava3rd.item03;

public class PublicStaticFinal {
	public static final PublicStaticFinal instance = new PublicStaticFinal();

	private PublicStaticFinal(){}

	protected Object readResolve(){
		return instance;
	}

	public static void doTest() {

	}

}