package com.coopnc.effectivejava3rd.item31;

import java.util.*;
import java.util.concurrent.ScheduledFuture;

public class Main {
    public static void main(String[] arg) {
        Stack<Number> numberStack = new Stack<>();
        Iterable<Integer> integers = null;
        // Interger는 Number의 하위 타입이니 논리적으로 잘 동작된다
        //numberStack.pushAll(integers);

        Collection<Object> numberCollection = new ArrayList<>();
        // Number는 Object의 하위 타입이니 논리적으로 잘 동작된다
        //numberStack.popAll(numberCollection);

    }
}

