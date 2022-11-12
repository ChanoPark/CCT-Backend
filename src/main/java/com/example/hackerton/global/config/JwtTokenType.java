package com.example.hackerton.global.config;

public enum JwtTokenType {
    ACCESS("access"), REFRESH("refresh");

    String value;

    JwtTokenType(String val) {
        this.value = val;
    }


    @Override
    public String toString() {
        return value;
    }

    public boolean equals(String value) {
        return this.value.equals(value);
    }
}