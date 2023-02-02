package com.coopnc.effectivejava3rd.item05;

import java.util.function.Supplier;

public class DITest {

    public static void doTest() {
        // 정적 유틸리티 클래스 구현
        boolean isChecked = SpellChecker.isValid("hello");
        System.out.println("isChecked = " + isChecked);

        // Singleton 구현
        boolean isChecked_V2 = SpellChecker_V2.INSTANCE.isValid("hello");
        System.out.println("isChecked_V2 = " + isChecked_V2);

        // 의존성 구현
        SpellChecker_V3 spellChecker_V3 = new SpellChecker_V3(new EnglishDictionary());
        boolean isChecked_V3 = spellChecker_V3.isValid("hello");
        System.out.println("isChecked_V3 = " + isChecked_V3);

        // Factory Method 패턴
        Supplier<Lexicon> dictionary = new Supplier<Lexicon>() {
            @Override
            public Lexicon get() {
                return new KoreaDictionary();
            }
        };
        SpellChecker_V4 spellChecker_V4 = new SpellChecker_V4(dictionary);
        boolean isChecked_V4 = spellChecker_V4.isValid("hello");
        System.out.println("isChecked_V4 = " + isChecked_V4);
    }
}
