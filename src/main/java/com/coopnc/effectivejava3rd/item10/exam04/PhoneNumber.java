package com.coopnc.effectivejava3rd.item10.exam04;

public final class PhoneNumber {
    private  final short areaCode, prefix, lineNum;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = rangeCheck(areaCode, 999,"지역코드");
        this.prefix = rangeCheck(prefix, 999,"프리픽스");
        this.lineNum = rangeCheck(lineNum, 9999,"가입자 번호");
    }

    private static short rangeCheck(int val, int max, String arg){
        if (val < 0 || val > max){
            throw new IllegalArgumentException(arg + " : " + val);
        }
        return (short) val;
    }

    @Override
    public boolean equals(Object obj) {
        // == 연산자로 자기 자신 참조 확인
        if (obj == this){
            return true;
        }

        // instanceof 연산자로 입력이 올바른 타입인지 확인
        if (!(obj instanceof PhoneNumber)){
            return false;
        }

        // 입력을 올바른 타입으로 형변환
        PhoneNumber phoneNumber = (PhoneNumber) obj;

        // 입력 객체와 자기 자신의 대응되는 '핵심' 필드들이 모두 일치하는 지 하나씩 검사
        // float, double을 제외한 기본타입은 == 연산자로 비교
        return phoneNumber.areaCode == areaCode
                && phoneNumber.lineNum == lineNum
                && phoneNumber.prefix == prefix;
    }
}

class PhoneNumberTest {
    public static void main( String[] args ) {

        PhoneNumber phoneNumber1 = new PhoneNumber(111, 555, 3333);
        PhoneNumber phoneNumber2 = new PhoneNumber(111, 555, 3333);
        System.out.println(phoneNumber1.equals(phoneNumber2));
        System.out.println(phoneNumber2.equals(phoneNumber1));
    }
}