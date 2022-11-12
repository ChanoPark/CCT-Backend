package com.example.hackerton.domain.user.service;

import com.example.hackerton.domain.user.Store;
import com.example.hackerton.domain.user.User;
import com.example.hackerton.domain.user.dto.LoginDto;
import com.example.hackerton.domain.user.dto.LoginReqDto;
import com.example.hackerton.global.config.LoginTokenResponse;

public interface AuthService {

    User userConfirmation(String id, String pw);

    Store getStore(String companyName);

    Store storeConfirmation(String id, String pw);

    LoginTokenResponse createLoginToken(LoginDto dto);

    User userSignUp(String id, String pw, String name, String tel);

    Store storeSignUp(String id, String pw, String name, String number, String tel, String address);

}
