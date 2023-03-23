package com.coopnc.effectivejava3rd.item18.exam1.coffee;

/**
 * 컴포지션을 이용한 카푸치노 만들기
 */
public class CappuccinoComposition extends ForwardingCoffee {
    private int ingredientCapacity;
    private int ingredientTemperature;
    private WaterIngredientType ingredientType;

    public CappuccinoComposition( CoffeeInterface coffee ) {
        super( coffee );
        ingredientType = WaterIngredientType.Milk;
    }

    /**
     * 커피와 우유를 비비거나 그에 준하는 어떤 행동
     */
    public void mixIngredient( int capacity, int temperature ) {
        ingredientCapacity = capacity;
        ingredientTemperature = temperature;
    }
}

