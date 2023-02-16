package com.coopnc.effectivejava3rd.item18.exam1.tea;

import com.coopnc.effectivejava3rd.item18.exam1.coffee.CoffeeInterface;

public class ForwardingTea {
	private final CoffeeInterface coffee;

	public ForwardingTea( CoffeeInterface coffee ) {
		this.coffee = coffee;
	}

	public void boilWater( int capacity, int temperature ) {
		coffee.boilWater( capacity, temperature );
	}
//	public void wetting( int wettingTime ) {
//		coffee.wetting( wettingTime );
//	}
	public void pourOver() {
		coffee.pourOver();
	}
}

