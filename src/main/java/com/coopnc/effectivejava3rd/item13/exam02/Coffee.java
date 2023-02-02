package com.coopnc.effectivejava3rd.item13.exam02;

import java.util.Objects;

/**
 * 커피
 *  - Object 클래스 명세에서의 clone 내용 확인
 */
public class Coffee extends Drink {
    private String beanName;
    private int inventoryNumber;

    public Coffee(final int waterCapacity, final String beanName) {
        super(waterCapacity);
        this.beanName = beanName;
        inventoryNumber = 1;
    }

    public Coffee(Drink drink) {
        super(drink.getWaterCapacity());
    }

//    public void setBeanName(String beanName) {
//        this.beanName = beanName;
//    }

    @Override
    public String toString() {
        return "Coffee{" +
                "beanName='" + beanName + '\'' +
                ", inventoryNumber=" + inventoryNumber +
                ", waterCapacity=" + waterCapacity +
                '}';
    }

    @Override
    public Coffee clone() throws RuntimeException {
        Coffee cloneCoffee = (Coffee) super.clone();
        ++cloneCoffee.inventoryNumber;

        return cloneCoffee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coffee)) return false;
        Coffee coffee = (Coffee) o;
        //return inventoryNumber == coffee.inventoryNumber && Objects.equals(beanName, coffee.beanName);
        return Objects.equals(beanName, coffee.beanName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beanName, inventoryNumber);
    }
}

