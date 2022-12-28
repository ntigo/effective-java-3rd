package com.coopnc.effectivejava3rd.item08.exam01;

import java.util.concurrent.CountDownLatch;

public class FinalizerSample {
    private final CountDownLatch countDownLatch;

    FinalizerSample(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    protected void finalize() {
        System.out.println("before call finalize - " + Thread.currentThread());
        String a = null;
        a.toString();
        System.out.println("after call finalize - " + Thread.currentThread());
        countDownLatch.countDown();
    }
}

class FinalizerTest {
    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        FinalizerSample finalizer = new FinalizerSample(countDownLatch);

        System.out.println(finalizer);

        finalizer = null;

        System.gc(); //

        System.out.println("end");

        countDownLatch.await();
    }
}
