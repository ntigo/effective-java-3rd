package com.coopnc.effectivejava3rd.item08.exam01;

import java.lang.ref.Cleaner;
import java.util.concurrent.CountDownLatch;

public class CleanerSample implements AutoCloseable {
    private static final Cleaner cleaner = Cleaner.create();
    private Cleaner.Cleanable cleanable;

    private final int idx;
    private final CountDownLatch countDownLatch;

    private static class State implements Runnable {
        private final int idx;
        private final CountDownLatch countDownLatch;

        private State(int idx, CountDownLatch countDownLatch) {
            this.idx = idx;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println("before call cleaner - idx: " + idx + ", thread: " + Thread.currentThread().getName());
            String a = null;
            a.toString();
            System.out.println("after call cleaner - idx: " + idx + ", thread: " + Thread.currentThread().getName());
            countDownLatch.countDown();
        }
    }

    CleanerSample(int idx, CountDownLatch countDownLatch) {
        this.idx = idx;
        this.countDownLatch = countDownLatch;
        this.cleanable = cleaner.register(this, new State(idx, countDownLatch));
    }

    @Override
    public void close() {
        cleanable.clean();
    }
}

class CleanerTest {
    public static void main(String[] args) throws Exception {
        final int MAX_OBJ_CNT = 100;

        CountDownLatch countDownLatch = new CountDownLatch(MAX_OBJ_CNT);

        CleanerSample[] arr = new CleanerSample[MAX_OBJ_CNT];
        for (int i = 0; i < MAX_OBJ_CNT; ++i) {
            arr[i] = new CleanerSample(i, countDownLatch);
        }

        for (int i = 0; i < MAX_OBJ_CNT; ++i) {
            arr[i] = null;
        }

        System.gc();

        while(true) {
            if (countDownLatch.getCount() == 0) {
                break;
            }
        }

        System.out.println("end");
    }
}
