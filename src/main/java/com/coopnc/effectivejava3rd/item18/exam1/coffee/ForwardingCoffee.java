package com.coopnc.effectivejava3rd.item18.exam1.coffee;

/**
 * 커피 전달 클래스
 *  - 나의 커피가 너에게 닿기를...!
 */
public class ForwardingCoffee implements CoffeeInterface {
    private final CoffeeInterface coffee;

    public ForwardingCoffee( CoffeeInterface coffee ) {
        this.coffee = coffee;
    }

    public void boilWater( int capacity, int temperature ) {
        coffee.boilWater( capacity, temperature );
    }
    public void wetting( int wettingTime ) {
        coffee.wetting( wettingTime );
    }
    public void pourOver() {
        coffee.pourOver();
    }

    @Override public boolean equals( Object o ) {
        return coffee.equals( o );
    }
    @Override public int hashCode() {
        return coffee.hashCode();
    }
    @Override public String toString() {
        return coffee.toString();
    }
}

