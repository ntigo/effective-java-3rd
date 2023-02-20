package com.coopnc.effectivejava3rd.item24.exam1;

public class OutsideClass {
    public void print() {
        System.out.println( "outside class ~" );
    }
    public class NonStaticClass {
        public void print() {
            System.out.println( "non static class ~" );
            OutsideClass.this.print();
        }
    }
    public static class StaticClass {
        public void print() {
            System.out.println( "static class ~" );
        }
    }
}

