package com.example.hackerton.domain.item;

public enum Category {

    CAMERA("1000"),
    LENS("2000"),
    ACCESSORIES("3000");

    private final String level;

    Category(String level) {
        this.level = level;
    }

    public String getLevelCd() {
        return level;
    }

    public static Category findByCode(String level){
        for(Category v : values()){
            if( v.getLevelCd().equals(level)){
                return v;
            }
        }
        return null;
    }

}
