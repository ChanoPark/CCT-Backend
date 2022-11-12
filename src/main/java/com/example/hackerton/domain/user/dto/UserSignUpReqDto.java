package com.example.hackerton.domain.user.dto;

import com.example.hackerton.domain.user.UserPermission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpReqDto {

    private String loginId;
    private String loginPw;
    private String name;
    private String tel;

}
