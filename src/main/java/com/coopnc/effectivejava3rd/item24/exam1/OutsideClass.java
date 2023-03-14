package com.coopnc.effectivejava3rd.item24.exam1;

public class OutsideClass {
    private String privateValue = "privateValue";
    protected String protectedValue = "protectedValue";
    public String publicValue = "publicValue";

    public void print() {
        NonStaticClass nonStaticClass = new NonStaticClass();
        System.out.println( "outside class print ~ " + nonStaticClass.publicValue );
        System.out.println( "outside class print ~ " + nonStaticClass.protectedValue );
        System.out.println( "outside class print ~ " + nonStaticClass.privateValue );
        nonStaticClass.privatePrint();

        StaticClass staticClass = new StaticClass();
        System.out.println( "outside class print ~ " + staticClass.publicValue );
        System.out.println( "outside class print ~ " + staticClass.protectedValue );
        System.out.println( "outside class print ~ " + staticClass.privateValue );
        staticClass.privatePrint();
    }
    private void privatePrint() {
        System.out.println( "outside class privatePrint ~" );
    }
    protected void protectedPrint() {
        System.out.println( "outside class protectedPrint ~" );
    }

    public class NonStaticClass {
        private String privateValue = "NonStaticClass.privateValue";
        protected String protectedValue = "NonStaticClass.protectedValue";
        public String publicValue = "NonStaticClass.publicValue";

        public void print() {
            System.out.println( "non static class ~ " + privateValue );
            System.out.println( "non static class ~ " + protectedValue );
            System.out.println( "non static class ~ " + publicValue );

            System.out.println( "non static class ~ " + OutsideClass.this.privateValue );
            System.out.println( "non static class ~ " + OutsideClass.this.protectedValue );
            System.out.println( "non static class ~ " + OutsideClass.this.publicValue );
            OutsideClass.this.print();
            OutsideClass.this.privatePrint();
            OutsideClass.this.protectedPrint();

            PrivateStaticClass privateStaticClass = new PrivateStaticClass();
        }

        private void privatePrint() {

        }
    }
    public static class StaticClass {
        private String privateValue = "StaticClass.privateValue";
        protected String protectedValue = "StaticClass.protectedValue";
        public String publicValue = "StaticClass.publicValue";

        public void print() {
            System.out.println( "static class ~ " );
            System.out.println( "static class ~ " + privateValue );
            System.out.println( "static class ~ " + protectedValue );
            System.out.println( "static class ~ " + publicValue );

//            System.out.println( "static class ~ " + OutsideClass.this.privateValue );
//            System.out.println( "static class ~ " + OutsideClass.this.protectedValue );
//            System.out.println( "static class ~ " + OutsideClass.this.publicValue );
//            OutsideClass.this.print();
//            OutsideClass.this.privatePrint();
//            OutsideClass.this.protectedPrint();
//
//            OutsideClass outsideClass = new OutsideClass();
//            outsideClass.print();
            PrivateStaticClass privateStaticClass = new PrivateStaticClass();
        }

        private void privatePrint() {

        }
    }
    private static class PrivateStaticClass {

    }
}

