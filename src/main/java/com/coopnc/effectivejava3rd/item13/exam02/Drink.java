package com.coopnc.effectivejava3rd.item13.exam02;

/**
 * 음료
 */
public class Drink implements Cloneable {
    protected int waterCapacity;

    public Drink(final int waterCapacity) {
        this.waterCapacity = waterCapacity;
    }

    public int getWaterCapacity() {
        return waterCapacity;
    }

    @Override
    public String toString() {
        return "Drink{" +
                "waterCapacity=" + waterCapacity +
                '}';
    }

    @Override
    public Drink clone() throws RuntimeException {
        try {
            return (Drink) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException();
        }
    }

//    /**
//     * 하위 클래스에서 clone을 구현하지 못하게 막을수도 있다...!
//     * @return
//     * @throws RuntimeException
//     */
//    @Override
//    protected final Object clone() throws CloneNotSupportedException {
//        throw new CloneNotSupportedException();
//    }
}
