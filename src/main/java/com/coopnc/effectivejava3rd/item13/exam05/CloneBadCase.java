package com.coopnc.effectivejava3rd.item13.exam05;

public class CloneBadCase extends CloneBaseBadCase {
    public CloneBadCase( final int weight, final EnumMamMalia mamMalia, final String name ) {
        super( weight, mamMalia, name );
    }

    @Override
    public String toString() {
        return "CloneBadCase{" +
                "weight=" + weight +
                ", mamMalia=" + mamMalia +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public void setMamMalia(EnumMamMalia mamMalia) {
        if ( mamMalia == EnumMamMalia.person && weight > 100 ) {
            this.mamMalia = EnumMamMalia.pig;
        } else {
            this.mamMalia = mamMalia;
        }
    }

    @Override
    public CloneBadCase clone() throws RuntimeException {
        CloneBadCase cloneBadCase = (CloneBadCase) super.clone();
        cloneBadCase.setMamMalia(mamMalia);

        return cloneBadCase;
        //return (CloneBadCase)super.clone();
    }
}
