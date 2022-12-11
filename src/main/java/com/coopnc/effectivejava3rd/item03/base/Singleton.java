package com.coopnc.effectivejava3rd.item03.base;



public class Singleton {

    private static Singleton instance;

    // 클래스 밖에서 인스턴스를 생성할수 없음.
    private Singleton(){}

//    멀티쓰레드 환경에서 안전하지 않음
    public static Singleton getInstance(){
        if (instance != null){
            instance = new Singleton();
        }

        return  instance;
    }
}
