package com.coopnc.effectivejava3rd.item5;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class Pin {
	private final Set< String > pinNumbers;

	public Pin( String prefix, int size) {
		List< String > lists = AutoRandomPinGenerator.pinGenerator( prefix, size );
		this.pinNumbers = lists.stream().map( String::new ).collect( toSet() );

	}

	public Set< String > getLottoNumbers() {
		return Collections.unmodifiableSet(pinNumbers);
	}

}
