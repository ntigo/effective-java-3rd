package com.coopnc.effectivejava3rd.item03.assist;

import java.util.function.Supplier;

public class SingletonSupplier {

    public void start( Supplier<ISingleton> supplier ) {
        ISingleton instance = supplier.get();
        instance.send( "test" );
    }
}
