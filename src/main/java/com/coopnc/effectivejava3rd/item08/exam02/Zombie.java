package com.coopnc.effectivejava3rd.item08.exam02;

import java.util.concurrent.CountDownLatch;

public class Zombie {
    static Zombie zombie;

    private CountDownLatch countDownLatch;

    Zombie(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("call finalize");
        zombie = this;
        countDownLatch.countDown();
    }
}

class ZombieTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Zombie zombie = new Zombie(countDownLatch);
        System.out.println(zombie);

        zombie = null;

        System.gc();

        countDownLatch.await();

        System.out.println(zombie);
        System.out.println(Zombie.zombie);

        System.gc();

        System.out.println("end");
    }
}
