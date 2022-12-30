package com.coopnc.effectivejava3rd.item05;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class SpellChecker_V4 {

    private final Lexicon dictionary;

    public SpellChecker_V4(Supplier<Lexicon> dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary.get());
    }

    public static boolean isValid(String word) {
        return true;
    }

    public static List<String> suggestions(String typo) {
        return new ArrayList<>();
    }
}
