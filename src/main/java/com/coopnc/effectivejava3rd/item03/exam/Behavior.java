package com.coopnc.effectivejava3rd.item03.briefing;

import com.coopnc.effectivejava3rd.item03.Basic;

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
