package com.coopnc.effectivejava3rd.item30;

import java.util.function.UnaryOperator;

public class IdentityFunction {
    public static UnaryOperator<Object> IDENTITY_FN = (t) -> t;

    @SuppressWarnings("unchecked")
    public static <T> UnaryOperator<T> identityFunction() {
        return (UnaryOperator<T>) IDENTITY_FN;
    }
}
