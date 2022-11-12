package com.example.hackerton.global.config;

import com.example.hackerton.global.common.CodeSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SuccessResponse extends ResponseAbs {
    private String refreshToken;

    public SuccessResponse(CodeSet code, String refreshToken) {
        super(code.getCode());
        this.refreshToken = refreshToken;
    }
}
