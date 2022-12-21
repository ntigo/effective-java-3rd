package com.coopnc.effectivejava3rd.item5;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class DIPin {
	private static final String DEFAUTL_PREFIX = "8443";
	private static final int DEFAUTL_ISSUE_SIZE = 10;

	private final Set< String > pinNumbers;

	public DIPin( RandomPinGenerator randomPinGenerator ) {
		List< String > lists = randomPinGenerator.pinGenerator( DEFAUTL_PREFIX, DEFAUTL_ISSUE_SIZE );
		this.pinNumbers = lists.stream().map( String::new ).collect( toSet() );
	}

	public Set< String > getPinNumbers() {
		return Collections.unmodifiableSet( pinNumbers );
	}
}
