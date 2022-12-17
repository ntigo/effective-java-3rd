package com.coopnc.effectivejava3rd.item03.briefing;

import java.util.function.Supplier;

public class SingletonTest {

    public void start( Supplier<ISingle> supplier ) {
        ISingle singleton = supplier.get();
        singleton.send( "hi-" );
    }
}
