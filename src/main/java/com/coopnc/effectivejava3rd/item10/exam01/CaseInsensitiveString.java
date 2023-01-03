package com.coopnc.effectivejava3rd.item10.exam01;

import java.util.Objects;

public final class CaseInsensitiveString {
    private final String s;

    public CaseInsensitiveString( String s ) {
        this.s = Objects.requireNonNull(s);
    }

    // 대칭성 위배
    @Override
    public boolean equals(Object o) {
        if ( o instanceof CaseInsensitiveString )
            return s.equalsIgnoreCase( ( (CaseInsensitiveString)o).s );
        if( o instanceof String)    // 한 방향으로만 작동
            return s.equalsIgnoreCase((String) o);

        return false;
    }

    // 수정
//    @Override
//    public boolean equals(Object o) {
//        return (o instanceof CaseInsensitiveString)
//                && ((CaseInsensitiveString) o).s.equalsIgnoreCase(s);
//    }
}

class CaseInsensitiveStringTest {
    public static void main(String[] args) throws Exception {
        CaseInsensitiveString caseInsensitiveStringUpper = new CaseInsensitiveString("Test");
        String testSmall = "test";
        System.out.println( caseInsensitiveStringUpper.equals(testSmall) ); //true
        System.out.println( testSmall.equals(caseInsensitiveStringUpper) ); //false

        // 수정후
//        CaseInsensitiveString caseInsensitiveStringLower = new CaseInsensitiveString("test");
//        System.out.println( caseInsensitiveStringUpper.equals(caseInsensitiveStringLower) );
//        System.out.println( caseInsensitiveStringLower.equals(caseInsensitiveStringUpper) );

//        List<CaseInsensitiveString> list = new ArrayList<>();
//        list.add(new CaseInsensitiveString("Test"));
//        System.out.println(list.contains("test")); //false or true
    }
}
