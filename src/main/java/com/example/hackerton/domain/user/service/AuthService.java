package com.example.hackerton.domain.user.service;

import com.example.hackerton.domain.user.User;
import com.example.hackerton.global.config.LoginTokenResponse;

public interface AuthService {

    User userConfirmation(String id, String pw);
    LoginTokenResponse createLoginToken(User user);
}
