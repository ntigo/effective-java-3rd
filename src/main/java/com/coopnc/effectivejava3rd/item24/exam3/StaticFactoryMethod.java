package com.coopnc.effectivejava3rd.item24.exam3;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;

public class StaticFactoryMethod implements FactoryMethod {
    /**
     * 정적 팩터리 메서드를 구현할 때 사용된다 ? ( item 20 - 1 )
     */
    public static FactoryMethod getInstance() {
        return () -> System.out.println( "헤헷 아무것도 안할건데요..?" );
    }

    @Override
    public void yoMethod() {
        System.out.println( "아이 참..." );
    }
}
