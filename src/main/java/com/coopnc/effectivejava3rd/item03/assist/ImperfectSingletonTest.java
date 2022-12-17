package com.coopnc.effectivejava3rd.item03.assist;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ImperfectSingletonTest {

    public static void doTest() {
        // 필드가 public 인 경우 getInstance 를 통하지 않고 필드에 직접 접근하여 변경 가능!!
        ImperfectSingleton.instance = null;

        // 생성자를 명시적으로 지정하지 않으면 직접 생성 가능!!
//        ImperfectSingleton imperfectSingleton = new ImperfectSingleton();

        // Thread safe 하지 않음. (상위 주석 후 수차례 반복 시 확인 가능)
        Runnable runnable = () -> {
            /*try {
                TimeUnit.NANOSECONDS.sleep( 1 );
            } catch ( InterruptedException e ) {
                throw new RuntimeException( e );
            }*/

            ImperfectSingleton singleton = ImperfectSingleton.getInstance();
            System.out.println( Thread.currentThread().getName() + " : " + singleton );
        };

        /*List<Thread> threads = new ArrayList<>();
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );

        int size = threads.size();
        for ( int i = 0; i < size; i++ ) {
            threads.get( i ).start();
        }*/

        ExecutorService executorService = Executors.newFixedThreadPool( Runtime.getRuntime().availableProcessors() * 2 );
        CompletableFuture.allOf( CompletableFuture.runAsync( runnable, executorService ),
                CompletableFuture.runAsync( runnable, executorService ),
                CompletableFuture.runAsync( runnable, executorService ),
                CompletableFuture.runAsync( runnable, executorService ),
                CompletableFuture.runAsync( runnable, executorService ),
                CompletableFuture.runAsync( runnable, executorService ),
                CompletableFuture.runAsync( runnable, executorService ),
                CompletableFuture.runAsync( runnable, executorService ),
                CompletableFuture.runAsync( runnable, executorService ),
                CompletableFuture.runAsync( runnable, executorService ),
                CompletableFuture.runAsync( runnable, executorService ),
                CompletableFuture.runAsync( runnable, executorService ),
                CompletableFuture.runAsync( runnable, executorService ));
        executorService.shutdown();
    }
}
