package com.coopnc.effectivejava3rd.item03.exam;

public class Factory {
	private static final Factory instance = new Factory();

	private Factory(){}

	public static Factory getInstance(){
		return instance;
	}


	public static void doTest() {

	}

}