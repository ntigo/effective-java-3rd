package com.coopnc.effectivejava3rd.item08.exam04;

import java.util.concurrent.CountDownLatch;

public class Zombie extends ParentZombie {
    static Zombie zombie;

    Zombie(CountDownLatch countDownLatch) {
        super();
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("call finalize");
        zombie = this;
    }
}

class ZombieTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Zombie zombie = null;
        try {
            zombie = new Zombie(countDownLatch);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(zombie);

        System.gc();

        Thread.sleep(1000L);

        System.out.println(zombie);
        System.out.println(Zombie.zombie);
        System.out.println(Zombie.A);

        System.out.println("end");
    }
}
