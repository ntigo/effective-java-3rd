package com.coopnc.effectivejava3rd.item03;

public class Basic {
	public static Basic basic = new Basic();

	private  Basic(){}

	public static Basic getInstance(){
//		if (instance == null){
//			instance = new Basic();
//		}
//		return instance;
		return basic;
	}


}
