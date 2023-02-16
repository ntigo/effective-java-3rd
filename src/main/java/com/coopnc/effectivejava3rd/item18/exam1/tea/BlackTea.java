package com.coopnc.effectivejava3rd.item18.exam1.tea;

import com.coopnc.effectivejava3rd.item18.exam1.coffee.Espresso;

public class BlackTea extends Espresso {
	private TeaType teaType;

	public BlackTea() {
		teaType = TeaType.BlackTea;
	}

	/**
	 * 커피로 뜸들이지 않고 티로 뜸들이기 위해 구현...
	 * @param wettingTime
	 */
	public void teaWetting( int wettingTime ) {
	}
}

