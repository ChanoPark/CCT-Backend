package com.example.hackerton.domain.user.service;

import com.example.hackerton.domain.user.User;
import com.example.hackerton.domain.user.dto.LoginReqDto;
import com.example.hackerton.domain.user.repository.AuthRepository;
import com.example.hackerton.global.config.JwtTokenProvider;
import com.example.hackerton.global.config.LoginTokenResponse;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthRepository authRepository;

    /*로그인 ID, PW 검증*/
    public User userConfirmation(String id, String pw) {
        return authRepository.login(id, pw);
    }

    /*검증 성공 시, 토큰 발급*/
    public LoginTokenResponse createLoginToken(User user) {
        String accessToken = jwtTokenProvider.createAccessToken(user);
        String refreshToken = jwtTokenProvider.createRefreshToken(user);
        return new LoginTokenResponse(accessToken,refreshToken);
    }
}
