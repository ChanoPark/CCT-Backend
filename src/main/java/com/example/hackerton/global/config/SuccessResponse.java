package com.example.hackerton.global.config;

import com.example.hackerton.global.common.CodeSet;

public class SuccessResponse extends ResponseAbs {
    private String refreshToken;

    public SuccessResponse(CodeSet code, String refreshToken) {
        super(code.getCode());
        this.refreshToken = refreshToken;
    }
}
