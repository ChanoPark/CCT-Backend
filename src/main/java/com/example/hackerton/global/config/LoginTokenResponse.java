package com.example.hackerton.global.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginTokenResponse extends ResponseAbs {
    private String accessToken;
    private String refreshToken;

    public LoginTokenResponse(String code, String accessToken, String refreshToken) {
        super(code);
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

}
