package com.coopnc.effectivejava3rd.item18.exam1.tea;

import com.coopnc.effectivejava3rd.item18.exam1.coffee.CoffeeInterface;

public class BlackTeaComposition extends ForwardingTea {
	private TeaType teaType;

	public BlackTeaComposition( CoffeeInterface coffee ) {
		super( coffee );
		teaType = TeaType.BlackTea;
	}

	/**
	 * 커피로 뜸들이지 않고 티로 뜸들이기 위해 구현...
	 * @param wettingTime
	 */
	public void teaWetting( int wettingTime ) {
	}
}

