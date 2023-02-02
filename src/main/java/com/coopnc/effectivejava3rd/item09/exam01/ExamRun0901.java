package com.coopnc.effectivejava3rd.item09.exam01;

import com.coopnc.effectivejava3rd.item09.TestException;

/**
 * item 9 exam01
 *  - try-with-resources 동작 테스트
 */
public class ExamRun0901 {
	public static void start() {
		try (
				TestAutoCloseable test1 = new TestAutoCloseable( 1 );
				TestAutoCloseable test2 = new TestAutoCloseable( 2 );
				TestAutoCloseable test3 = new TestAutoCloseable( 3 )
		) {
			System.out.println( "try content" );
			throw new Exception( "try block error" );
		} catch ( TestException e ) {
			System.out.println( e );
		} catch ( Exception e ) {
			System.out.println( e );
		}
	}
}

