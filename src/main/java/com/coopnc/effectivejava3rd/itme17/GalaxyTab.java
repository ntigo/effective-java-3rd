package com.coopnc.effectivejava3rd.itme17;

public final class GalaxyTab {

    private final Keyboard keyboard;

    public GalaxyTab(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    @Override
    public String toString() {
        return keyboard.getName();
    }

    public static void main(String[] args) {
        Keyboard keyboard1 = new Keyboard("mac");
        GalaxyTab tab = new GalaxyTab(keyboard1);

        System.out.println( tab);
        keyboard1.setName("dell");

        System.out.println(tab);

    }
}
