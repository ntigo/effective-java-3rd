package com.coopnc.effectivejava3rd.item03.exam;

public class Synchronized {
	private static Synchronized instance;

	private Synchronized(){
	}

	// mutex , semaphore
	// 상호 배타적인 접근을 유도하는 방법, mutex = binary semaphore
	public static synchronized Synchronized getInstance(){
		if (instance == null){
			instance = new Synchronized();
		}

		return instance;
	}

	public static void doTest() {

	}


}