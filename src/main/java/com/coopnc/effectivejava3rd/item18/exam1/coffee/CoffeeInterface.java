package com.coopnc.effectivejava3rd.item18.exam1.coffee;

public interface CoffeeInterface {
    /**
     * 물을 끓임, 또는 물의 온도를 원하는 만큼 올리는 행위
     * @param capacity 물 용량
     * @param temperature 물 온도
     */
    void boilWater( int capacity, int temperature );

    /**
     * 뜨...ㅁ 들임
     * @param wettingTime 뜸들이는 시간
     */
    void wetting( int wettingTime );

    /**
     * 일본, 한국에서는 보통 drip coffee 라고 부름, 찬물을 사용하면 cold brew.. 이것은 일본에서 유래한것이라 한다.
     * 하지만 pour over 가 근본... 뜨거운 물을 통해 뜸을 들이면서 필터를 통해 정성껏 커피를 추출하는 것이다!
     */
    void pourOver();
}
