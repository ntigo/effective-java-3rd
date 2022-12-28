package com.coopnc.effectivejava3rd.item07.exam04;

public class CallbackSample {
    public static void main(String[] args) throws Exception {
        Callee callee = new Callee();
        Callback callback = new Callback();
        callee.setCallback(callback);

        callback = null;

        System.gc();

        for(int i=0; i<5; i++){ //메시지 발송을 5번까지 보낸다
            callee.onInputMessage("i = " + i);
        }
    }
}
