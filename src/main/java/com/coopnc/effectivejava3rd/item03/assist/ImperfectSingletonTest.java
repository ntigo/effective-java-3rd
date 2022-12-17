package com.ntigo.study.effectivejava3rd.item03.assist;

import java.util.ArrayList;
import java.util.List;

public class ImperfectSingletonTest {

    public static void doTest() {
        // 필드가 public 인 경우 getInstance 를 통하지 않고 필드에 직접 접근하여 변경 가능!!
        ImperfectSingleton.instance = null;

        // 생성자를 명시적으로 지정하지 않으면 직접 생성 가능!!
        ImperfectSingleton imperfectSingleton = new ImperfectSingleton();

        // Thread safe 하지 않음. (상위 주석 후 수차례 반복 시 확인 가능)
        Runnable runnable = () -> {
            ImperfectSingleton singleton = new ImperfectSingleton();
            System.out.println( singleton );
        };

        List<Thread> threads = new ArrayList<>();
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );

        for ( int i = 0; i < threads.size(); i++ ) {
            Thread thread = threads.get( i );
            thread.start();
        }
    }
}
