package com.coopnc.effectivejava3rd.item24.exam1;

public class OutsideClassExample {
    /**
     * 비 정적 멤버 클래스는 바깥 인스턴스 없이는 생성할 수 없음
     */
    public void nonStaticClass() {
        OutsideClass outsideClass = new OutsideClass();
        OutsideClass.NonStaticClass nonStaticClass = outsideClass.new NonStaticClass();
        outsideClass = null;
        nonStaticClass.print();
    }
    /**
     * 정적 클래스는 바깥 인스턴스 생성 없이 생성 가능
     */
    public void staticClass() {
        OutsideClass.StaticClass staticClass = new OutsideClass.StaticClass();
        staticClass.print();
    }
}
