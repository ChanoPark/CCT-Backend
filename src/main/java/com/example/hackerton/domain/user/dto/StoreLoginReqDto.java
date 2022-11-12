package com.example.hackerton.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StoreLoginReqDto {
    @NotNull
    private String loginId;
    @NotNull
    private String loginPw;
}
