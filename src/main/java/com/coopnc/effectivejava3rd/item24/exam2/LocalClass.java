package com.coopnc.effectivejava3rd.item24.exam2;

/**
 * 지ㅣㅣㅣㅣ역 클랫쓰
 */
public class LocalClass {
    private String localPrivateValue = "허허 거참... 사용할 수 없대도?";
    public String localPublicValue = "난 사용할 수 있단다";
    private static String localStaticValue = "난 어디든지 사용할 수 이찌...";


    // 으음 정적 문매ㅐㄱ
    //public static void test() {
    // 비 정적 문매애ㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㄱ
    public void test() {
        LocalClass localClass = new LocalClass();

        class RealLocalClass {
            private String oWong = "오옹?";

            public String getRodong() {
                return "그건 너의 노동";
            }

//            public String useLocalPrivateValue() {
//                return "널 사용하고 싶은데? " + localPrivateValue;
//            }

            public String useLocalPrivateValue() {
                return "널 사용하고 싶은데? " + LocalClass.this.localPrivateValue;
            }

            public String useLocalPulbicValue() {
                return "너는 당연히 사용할 수 있겠지? " + localClass.localPublicValue;
            }

            public String useLocalStaticValue() {
                return "넌 어디든지 이찌 + " + localStaticValue;
            }
        }

        RealLocalClass realLocalClass = new RealLocalClass();
        System.out.println(realLocalClass.getRodong());
        System.out.println(realLocalClass.useLocalPrivateValue());
        System.out.println(realLocalClass.useLocalPulbicValue());
        System.out.println(realLocalClass.useLocalStaticValue());
    }
}
