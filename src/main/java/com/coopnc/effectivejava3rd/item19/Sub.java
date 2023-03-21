package com.coopnc.effectivejava3rd.item19;

import java.time.Instant;

public class Sub extends Super {
	private final Instant instant;

	public Sub() {
		this.instant = Instant.now();
	}

	@Override
	public void overrideMe() {
		System.out.println( instant );
	}
}