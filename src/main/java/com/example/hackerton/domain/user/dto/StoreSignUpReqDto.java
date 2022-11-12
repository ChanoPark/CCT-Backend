package com.example.hackerton.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StoreSignUpReqDto {
    private String loginId;
    private String loginPw;
    private String name;
    private String number;
    private String tel;
    private String address;
}
