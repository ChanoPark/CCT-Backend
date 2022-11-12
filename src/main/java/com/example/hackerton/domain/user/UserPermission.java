package com.example.hackerton.domain.user;

public enum UserPermission {
    CONSUMER( "1000"),
    PROVIDER( "2000");

    private final String level;

    UserPermission(String level) {
        this.level = level;
    }

    public String getLevelCd() {
        return level;
    }

    public static UserPermission findByCode(String level){
        for(UserPermission v : values()){
            if( v.getLevelCd().equals(level)){
                return v;
            }
        }
        return null;
    }


}
