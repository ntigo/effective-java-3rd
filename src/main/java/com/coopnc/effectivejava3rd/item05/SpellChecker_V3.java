package com.coopnc.effectivejava3rd.item05;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SpellChecker_V3 {
    private final Lexicon dicionary;

    public SpellChecker_V3(Lexicon dicionary) {
        this.dicionary = Objects.requireNonNull(dicionary);
    }

    public static boolean isValid(String word) {
        return true;
    }

    public static List<String> suggestions(String typo) {
        return new ArrayList<>();
    }
}
