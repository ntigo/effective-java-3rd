package com.coopnc.effectivejava3rd.item13.exam05;

public class CloneBaseBadCase implements Cloneable {
    protected int weight;
    protected EnumMamMalia mamMalia;
    protected String name;

    public CloneBaseBadCase(final int weight, final EnumMamMalia mamMalia, final String name ) {
        this.weight = weight;
        this.mamMalia = mamMalia;
        this.name = name;
    }

    public EnumMamMalia getMamMalia() {
        return mamMalia;
    }
    public void setMamMalia(EnumMamMalia mamMalia) {
        this.mamMalia = mamMalia;
    }

    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public CloneBaseBadCase clone() throws RuntimeException {
        try {
            return (CloneBaseBadCase) super.clone();
        } catch( CloneNotSupportedException e ) {
            throw new RuntimeException();
        }
    }
}
