package com.coopnc.effectivejava3rd.item5;

import java.util.List;

public interface RandomPinGenerator {
	List< String > pinGenerator( String preFix, int size );
}
