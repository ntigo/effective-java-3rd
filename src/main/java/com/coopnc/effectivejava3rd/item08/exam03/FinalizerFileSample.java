package com.coopnc.effectivejava3rd.item08.exam03;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.CountDownLatch;

public class FinalizerFileSample {
    private final FileInputStream fileInputStream;
    FinalizerFileSample() {
        try {
            this.fileInputStream = new FileInputStream(new File("documents/item01.md"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("close");
        this.fileInputStream.close();
    }
}

class FinalizerFileTest {
    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        FinalizerFileTest finalizerFileTest = new FinalizerFileTest();
        finalizerFileTest.test();
        finalizerFileTest.test();

        System.gc();

        System.out.println("end");

        countDownLatch.await();
    }

    private void test() {
        FinalizerFileSample finalizer = new FinalizerFileSample();
        System.out.println("call test");
    }
}
