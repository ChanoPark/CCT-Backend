package com.example.hackerton.domain.user.service;

import com.example.hackerton.domain.user.Store;
import com.example.hackerton.domain.user.User;
import com.example.hackerton.domain.user.UserPermission;
import com.example.hackerton.domain.user.dto.LoginDto;
import com.example.hackerton.domain.user.repository.AuthStoreRepository;
import com.example.hackerton.domain.user.repository.AuthUserRepository;
import com.example.hackerton.global.config.JwtTokenProvider;
import com.example.hackerton.global.config.LoginTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthUserRepository authUserRepository;
    private final AuthStoreRepository authStoreRepository;

    /*로그인 ID, PW 검증*/
    public User userConfirmation(String id, String pw) {
        return authUserRepository.login(id, pw);
    }

    public Store storeConfirmation(String id, String pw) {
        return authStoreRepository.login(id, pw);
    }

    public User userSignUp(String id, String pw, String name, String tel) {
        User user = User.builder()
                .loginId(id)
                .loginPw(pw)
                .name(name)
                .tel(tel)
                .role(UserPermission.CONSUMER)
                .level("1000")
                .build();

        return authUserRepository.save(user);
    }

    @Transactional
    @Override
    public Store storeSignUp (String id, String pw, String name, String number, String tel, String address) {
        Store store = Store.builder()
                .loginId(id)
                .loginPw(pw)
                .name(name)
                .number(number)
                .tel(tel)
                .address(address)
                .point(0)
                .role(UserPermission.PROVIDER)
                .level("2000")
                .build();

        return authStoreRepository.save(store);
    }

    /*검증 성공 시, 토큰 발급*/
    public LoginTokenResponse createLoginToken(LoginDto dto) {
        String accessToken = jwtTokenProvider.createAccessToken(dto);
        String refreshToken = jwtTokenProvider.createRefreshToken(dto);
        return new LoginTokenResponse(accessToken,refreshToken);
    }
}
