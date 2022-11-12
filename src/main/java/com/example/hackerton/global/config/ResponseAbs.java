package com.example.hackerton.global.config;

import com.example.hackerton.global.common.CodeSet;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public abstract class ResponseAbs implements Response {
    private String code = CodeSet.OK.getCode();

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }
}
