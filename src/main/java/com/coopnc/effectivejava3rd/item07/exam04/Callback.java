package com.coopnc.effectivejava3rd.item07.exam04;

public class Callback implements CallBackI {
	public void print(String str) {
		System.out.println("callback - " + str);
	}
}