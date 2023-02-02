package com.coopnc.effectivejava3rd.item03.exam;

import com.coopnc.effectivejava3rd.item03.exam.Basic;

import java.util.function.Supplier;

public class Behavior {

    public Behavior(Supplier<ISingle> supplier) {
        ISingle iSingle = supplier.get();
        iSingle.sendSms();
    }

    public Behavior() {
    }

    public Behavior(int i ){

    }

    public Behavior(int i, String str ){

    }
}
