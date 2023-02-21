package com.coopnc.effectivejava3rd.item24.exam2;

public class AnonymousClass {
    private String name = "rodongja";

    // 응 private 이면 안되, public / protected 은 되
    protected String holy = "yo holy";

    public AnonymousClass() {
        System.out.println( "생성자가 실행되었습니까?" );
    }

    public void print() {
        System.out.println( "original class~" );
    }
    public void anonymousPrint() {
        // 익명 클랫쓰
        AnonymousClass anonymousClass = new AnonymousClass() {
            @Override
            public void print() {
                System.out.println( "no name class ~ " + AnonymousClass.this.name );
            }
        };
        anonymousClass.print();
    }

    public static void yoPrint() {
        //어... 이런거 안되!
        //AnonymousClass anonymousClass = new AnonymousClass() extends Calculator {
//        AnonymousClass anonymousClassYo = new AnonymousClass() implements TestInterface {
//            @Override
//            public void wowJustMove() {
//                System.out.println( "what's going on" );
//            }
//        }
        // 익명 클랫쓰
        AnonymousClass anonymousClass = new AnonymousClass() {
            private static final String data = "123";

            @Override
            public void print() {
                // 응 안되
                //System.out.println( "no name class ~ " + AnonymousClass.this.name );
                //System.out.println( "no name class ~ " + name );
                // 응 되
                System.out.println( "no name class ~ " + holy + ", data : " + data );
            }
        };
        anonymousClass.print();
    }
}


