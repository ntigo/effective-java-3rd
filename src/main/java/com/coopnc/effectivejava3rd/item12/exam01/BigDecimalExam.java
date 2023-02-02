package main.java.com.coopnc.effectivejava3rd.item12.exam01;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigDecimalExam {
    public static void main(String[] args) {
        //BigDecimal(실수형)
        BigDecimal number01 = new BigDecimal("10000.12345");
        BigDecimal number02 = new BigDecimal("1000");

        System.out.println(number01.add(number02));

        //BigInteger(정수형)
        BigInteger number03 = new BigInteger("10000");
        BigInteger number04 = new BigInteger("1000");

        System.out.println(number03.divide(number04));

        // int, BigInteger 차이 비교
        int Max = Integer.MAX_VALUE;
        String strMax = String.valueOf(Max);

        BigInteger number05 = new BigInteger(strMax);

        System.out.println(number05.add(number04));
    } 
}
