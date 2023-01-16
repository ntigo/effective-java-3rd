package com.coopnc.effectivejava3rd.item13.exam06;

import java.util.Arrays;

public class CloneGoodArray implements Cloneable {
    private String[] personList;

    public CloneGoodArray( String... personList ) {
        this.personList = personList;
    }

    public String[] getPersonList() {
        return personList;
    }
    public void setPersonList(String[] personList) {
        this.personList = personList;
    }

    @Override
    public String toString() {
        return "CloneGoodArray{" +
                "personList=" + Arrays.toString(personList) +
                '}';
    }

    @Override
    public CloneGoodArray clone() throws RuntimeException {
        try {
            return (CloneGoodArray)super.clone();
        } catch( CloneNotSupportedException e ) {
            throw new RuntimeException();
        }
    }
}
