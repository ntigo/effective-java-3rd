package com.coopnc.effectivejava3rd.item03;

public class DoubleCheckedLocking {
	private static volatile DoubleCheckedLocking instance;

	private DoubleCheckedLocking(){
	}

	public static DoubleCheckedLocking getInstance(){
		if (instance == null){
			synchronized ( DoubleCheckedLocking.class ){
				if(instance == null){
					instance = new DoubleCheckedLocking();
				}
			}
		}

		return instance;
	}
}