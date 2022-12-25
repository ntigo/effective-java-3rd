package com.coopnc.effectivejava3rd.item05;

import java.util.ArrayList;
import java.util.List;

public class SpellChecker_V2 {

    //Singleton Instance 생성
    public static final SpellChecker_V2 INSTANCE = new SpellChecker_V2();
    private SpellChecker_V2() {
        //객체 생성 방지
    }

    public static boolean isValid(String word) {
        return true;
    }

    public static List<String> suggestions(String typo) {
        return new ArrayList<>();
    }
}
