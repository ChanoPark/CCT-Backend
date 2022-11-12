package com.example.hackerton.domain.user.controller;

import com.example.hackerton.domain.user.Store;
import com.example.hackerton.domain.user.User;
import com.example.hackerton.domain.user.dto.*;
import com.example.hackerton.domain.user.service.AuthService;
import com.example.hackerton.global.common.CodeSet;
import com.example.hackerton.global.common.EndPoint;
import com.example.hackerton.global.config.LoginTokenResponse;
import com.example.hackerton.global.config.RejectResponse;
import com.example.hackerton.global.config.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @PostMapping(EndPoint.AUTH_USER_LOGIN)
    public ResponseEntity<?> userLogin(@RequestBody LoginReqDto request) {
        User user = authService.userConfirmation(request.getLoginId(), request.getLoginPw());
        if (user == null || user.getLoginId() == null ) {
            return ResponseEntity.ok(new RejectResponse(CodeSet.AUTH_LOGIN_FAIL));
        }

        LoginDto dto = new LoginDto();
        dto.setUser(user);

        LoginTokenResponse loginResponse = authService.createLoginToken(dto);

        return ResponseEntity.ok()
                .header("Authorization", loginResponse.getAccessToken())
                .body(new SuccessResponse(CodeSet.OK, loginResponse.getRefreshToken()));
    }

    @PostMapping(EndPoint.AUTH_STORE_LOGIN)
    public ResponseEntity<?> storeLogin(@RequestBody StoreLoginReqDto request) {
        Store store = authService.storeConfirmation(request.getLoginId(), request.getLoginPw());
        if (store == null || store.getLoginId() == null ) {
            return ResponseEntity.ok(new RejectResponse(CodeSet.AUTH_LOGIN_FAIL));
        }

        LoginDto dto = new LoginDto();
        dto.setStore(store);

        LoginTokenResponse loginResponse = authService.createLoginToken(dto);

        return ResponseEntity.ok()
                .header("Authorization", loginResponse.getAccessToken())
                .body(new SuccessResponse(CodeSet.OK, loginResponse.getRefreshToken()));
    }

    @PostMapping(EndPoint.AUTH_USER_SIGNUP)
    public ResponseEntity<?> userSignUp(@RequestBody UserSignUpReqDto request) {
        User user = authService.userSignUp(request.getLoginId(), request.getLoginPw(), request.getName(), request.getTel());
        if (user == null || user.getLoginId() == null ) {
            return ResponseEntity.ok(new RejectResponse(CodeSet.AUTH_SIGNUP_FAIL));
        }

        LoginDto dto = new LoginDto();
        dto.setUser(user);

        LoginTokenResponse loginResponse = authService.createLoginToken(dto);

        return ResponseEntity.ok()
                .header("Authorization", loginResponse.getAccessToken())
                .body(new SuccessResponse(CodeSet.OK, loginResponse.getRefreshToken()));
    }

    @PostMapping(EndPoint.AUTH_STORE_SIGNUP)
    public ResponseEntity<?> storeSignUp(@RequestBody StoreSignUpReqDto request) {
        Store store = authService.storeSignUp(request.getLoginId(), request.getLoginPw(), request.getName(), request.getNumber(), request.getTel(), request.getAddress());
        if (store == null || store.getLoginId() == null ) {
            return ResponseEntity.ok(new RejectResponse(CodeSet.AUTH_SIGNUP_FAIL));
        }

        LoginDto dto = new LoginDto();
        dto.setStore(store);

        LoginTokenResponse loginResponse = authService.createLoginToken(dto);
        return ResponseEntity.ok()
                .header("Authorization", loginResponse.getAccessToken())
                .body(new SuccessResponse(CodeSet.OK, loginResponse.getRefreshToken()));
    }

}
