package com.coopnc.effectivejava3rd.item01.briefing;


public class Member {
    private final String name;
    private final String birth;
    private final String address;
    private final String gender;
    private final String height;

    private Member( Builder builder ) {
        this.name = builder.name;
        this.birth = builder.birth;
        this.gender = builder.gender;
        this.address = builder.address;
        this.height = builder.height;
    }

    public static class Builder {
        private final String name;
        private final String birth;
        private String address;
        private String gender;
        private String height;

        public Builder( String name, String birth ) {
            this.name = name;
            this.birth = birth;
        }

        public Builder setAddress( String address ) {
            this.address = address;
            return this;
        }

        public Builder setGender( String gender ) {
            this.gender = gender;
            return this;
        }

        public Builder setHeight( String height ) {
            this.height = height;
            return this;
        }

        public Member build() {
            return new Member( this );
        }
    }
}
