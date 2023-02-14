package com.coopnc.effectivejava3rd.item22.exam3;

import static com.coopnc.effectivejava3rd.item22.exam2.PhysicalConstants.*;

public class Test {
	double atoms(double mols) {
		return AVOGADROS_NUMBER * mols;
	}
	// PhysicalConstants를 빈번히 사용 한다면 정적 임포트가 값어치를 한다.
}
