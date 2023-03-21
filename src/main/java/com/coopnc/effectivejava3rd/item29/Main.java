package com.coopnc.effectivejava3rd.item29;

import com.coopnc.effectivejava3rd.item29.exam1.Stack;

public class Main {
    public static void main(String args[]) {
        Stack stack = new Stack();
        stack.push(1);
        String item = (String) stack.pop();
        System.out.println(item);
    }
}
