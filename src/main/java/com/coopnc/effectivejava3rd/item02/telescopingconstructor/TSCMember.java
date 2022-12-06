package com.coopnc.effectivejava3rd.item02.telescopingconstructor;

public class TSCMember {
    private final String name; // Requirement
    private final String birth; // Requirement
    private final String bloodType;
    private final int tall;
    private final String cellular;

    public TSCMember(String name, String birth) {
        this(name, birth, "");
    }

    public TSCMember(String name, String birth, String bloodType) {
        this(name, birth, bloodType, 0);
    }

    public TSCMember(String name, String birth, String bloodType, int tall) {
        this(name, birth, bloodType, tall, "");
    }

    public TSCMember(String name, String birth, String bloodType, int tall, String cellular) {
        this.name = name;
        this.birth = birth;
        this.bloodType = bloodType;
        this.tall = tall;
        this.cellular = cellular;
    }
}
