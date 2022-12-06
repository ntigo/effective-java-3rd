package com.coopnc.effectivejava3rd.item02.javabenas;

public class JBMember {
    private String name; // Requirement
    private String birth; // Requirement
    private String bloodType;
    private int tall;
    private String cellular;

    public JBMember() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public void setTall(int tall) {
        this.tall = tall;
    }

    public void setCellular(String cellular) {
        this.cellular = cellular;
    }
}
