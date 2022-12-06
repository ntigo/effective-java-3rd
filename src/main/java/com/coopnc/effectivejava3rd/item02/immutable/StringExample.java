package com.coopnc.effectivejava3rd.item02.immutable;

public class StringExample {

    public static void doTest() {
        String a = "a";
        String b = new String( "a" );
        String c = "a";

        System.out.println( a == b );
        System.out.println( a == c );

        String d = a.intern();
        System.out.println( d );

        System.out.println( "a: " + System.identityHashCode( a ) );
        System.out.println( "b: " + System.identityHashCode( b ) );
        System.out.println( "c: " + System.identityHashCode( c ) );
        System.out.println( "d: " + System.identityHashCode( d ) );
    }
}
