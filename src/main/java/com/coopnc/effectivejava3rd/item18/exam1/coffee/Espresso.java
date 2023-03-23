package com.coopnc.effectivejava3rd.item18.exam1.coffee;

public class Espresso implements CoffeeInterface {
    protected int waterCapacity;
    protected int waterTemperature;
    protected int wettingTime;
    protected CoffeeType coffeeType;

    public Espresso() {
        coffeeType = CoffeeType.Espresso;
    }

    public void boilWater( int capacity, int temperature ) {
        waterCapacity = capacity;
        waterTemperature = temperature;
    }
    public void wetting( int wettingTime ) {
        waterTemperature = wettingTime;
    }
    public void pourOver() {
    }

    public int getWaterCapacity() {
        return waterCapacity;
    }
    public int getWaterTemperature() {
        return waterTemperature;
    }
    public int getWettingTime() {
        return wettingTime;
    }
    public CoffeeType getCoffeeType() {
        return coffeeType;
    }
}


