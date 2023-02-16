package com.coopnc.effectivejava3rd.item18.exam1.coffee;

/**
 * 만들어진 커피에 우유만 비비면 되겠군요
 */
public class Cappuccino extends Espresso {
    private int ingredientCapacity;
    private int ingredientTemperature;
    private WaterIngredientType ingredientType;

    public Cappuccino() {
        super();
        ingredientType = WaterIngredientType.Milk;
    }

    /**
     * 커피와 우유를 비비거나 그에 준하는 어떤 행동
     * @param capacity 재료의 용량
     * @param temperature 재료의 온도...!
     */
    private void mixIngredient( int capacity, int temperature ) {
        ingredientCapacity = capacity;
        ingredientTemperature = temperature;
    }
}

