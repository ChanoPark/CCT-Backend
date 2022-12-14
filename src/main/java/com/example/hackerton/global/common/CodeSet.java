package com.example.hackerton.global.common;

public enum CodeSet {
    OK("0000","OK"),

    AUTH_ACCESS_TOKEN_EXPIRE("1203", "Access Token Expire."),
    AUTH_LOGIN_FAIL("1100", "Login Fail."),
    AUTH_SIGNUP_FAIL("1101", "Signup Fail."),
    ITEM_SAVE_SUCCESS("2000", "ITEM SAVE SUCCESS."),
    ITEM_SAVE_FAIL("2100", "Signup Fail.");


    private String code;
    private String message;


    CodeSet(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage(){
        return message;
    }

    public static CodeSet findByCode(String code){
        for(CodeSet v : values()){
            if( v.getCode().equals(code)){
                return v;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return code;
    }
}
