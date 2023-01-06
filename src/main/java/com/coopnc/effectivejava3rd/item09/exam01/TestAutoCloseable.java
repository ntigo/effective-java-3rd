package com.coopnc.effectivejava3rd.item09.exam01;

public class TestAutoCloseable implements AutoCloseable {
	private final int number;

	public TestAutoCloseable( final int number ) {
		this.number = number;
	}

	@Override
	public void close() throws Exception {
		// call check
		System.out.println( "call - close method [ number : " + number + " ]" );

		// throw exception
		//throw new Exception( "throw exception - close method [ number : " + number + " ]" );

		// try-catch
//		try {
//			throw new Exception( "throw exception - close method [ number : " + number + " ]" );
//		} catch ( Exception e ) {
//			System.out.println( e.toString() );
//		}

		// close 처리가 지연되는 경우
		//Thread.sleep( 2000 );
	}
}
