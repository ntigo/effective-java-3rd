package com.coopnc.effectivejava3rd.item02.builder;

public class BMember {
    private final String name; // Requirement
    private final String birth; // Requirement
    private final String bloodType;
    private final int tall;
    private final String cellular;

    private BMember(Builder builder) {
        this.name = builder.name;
        this.birth = builder.birth;
        this.bloodType = builder.bloodType;
        this.tall = builder.tall;
        this.cellular = builder.cellular;
    }

    public static class Builder {
        private final String name; // Requirement
        private final String birth; // Requirement
        private String bloodType;
        private int tall;
        private String cellular;

        public Builder(String name, String birth) {
            this.name = name;
            this.birth = birth;
        }

        public Builder setBloodType(String bloodType) {
            this.bloodType = bloodType;
            return this;
        }

        public Builder setTall(int tall) {
            this.tall = tall;
            return this;
        }

        public Builder setCellular(String cellular) {
            this.cellular = cellular;
            return this;
        }

        public BMember build() {
            return new BMember(this);
        }
    }
}
