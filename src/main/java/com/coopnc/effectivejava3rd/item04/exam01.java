package com.coopnc.effectivejava3rd.item04.exam01;

public class exam01 {
    class DateUtility{
        private DateUtility(){
            /**
             * 클래스 내부에서도 호출이 안되도록 막는다.
             * 생성되면 에러 발생
             */
            throw new AssertionError();
        }
    }

    public static void main(String[] args){

        /** 적적한주석으로 직관성을 높일 수 있다
        *DateUtility dateUtility = new DateUtility()
         * private 처리 후, instance화가 불가능 하다.
    }*/
}
}
