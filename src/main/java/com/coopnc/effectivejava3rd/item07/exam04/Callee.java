package com.coopnc.effectivejava3rd.item07.exam04;

public class Callee {
	private CallBackI callback;

	public Callee() {
		this.callback = null;
	}

	public void setCallback(CallBackI callback) {
		this.callback = callback;
	}
	
	public void onInputMessage(String str) {
		if(this.callback != null) { // callback 처리
			this.callback.print(str);
		}
	}
}