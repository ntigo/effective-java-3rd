package com.coopnc.effectivejava3rd.item5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AutoRandomPinGenerator {

	private AutoRandomPinGenerator() {
	}

	public static List< String > pinGenerator( String preFix, int size ) {
		List< String > pinList = new ArrayList<>();
		StringBuilder pinNo = new StringBuilder();

		for ( int i = 0; i < size; i++ ) {
			Random random = new Random();
			StringBuilder sb = new StringBuilder();
			pinNo = new StringBuilder();

			for ( int j = 0; j < 12; j++ ) {
				int value = random.nextInt( 10 );
				sb.append( value );
			}

			pinNo.append( preFix );
			pinNo.append( sb.toString() );

			pinList.add( pinNo.toString() );
		}

		return pinList;
	}
}
