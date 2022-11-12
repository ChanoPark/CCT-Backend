package com.example.hackerton.global.config;

import com.example.hackerton.global.common.CodeSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RejectResponse extends ResponseAbs {
    private String message;

    public RejectResponse(String code, String message) {
        super(code);
        this.message = message;
    }

    public RejectResponse(CodeSet codeset) {
        super(codeset.getCode());
        this.message = codeset.getMessage();
    }
}
