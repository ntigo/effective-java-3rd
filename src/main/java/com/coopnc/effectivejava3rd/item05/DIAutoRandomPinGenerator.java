package com.coopnc.effectivejava3rd.item05;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.toList;

public class DIAutoRandomPinGenerator implements RandomPinGenerator {

	public List< String > generator( String preFix, int size ) {
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

	@Override
	public List< String > pinGenerator( String preFix, int size ) {
		List< String > lists = generator( preFix, size );
		return lists.stream().limit(size).collect(toList());
	}
}
