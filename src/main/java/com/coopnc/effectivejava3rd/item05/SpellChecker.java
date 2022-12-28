package com.coopnc.effectivejava3rd.item05;

import java.util.ArrayList;
import java.util.List;

public class SpellChecker {
    // Dictionary 객체가 고정 -> 변경이 어렵움
    private static final Lexicon dictionary = new KoreaDictionary();

    private SpellChecker() {
        // 객체 생성 방지
    }

    public static boolean isValid(String word) { return true; }

    public static List<String> suggestions(String typo) {
        return new ArrayList<>();
    }
}
