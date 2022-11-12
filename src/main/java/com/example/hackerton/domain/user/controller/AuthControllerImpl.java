package com.example.hackerton.domain.user.controller;

import com.example.hackerton.domain.user.User;
import com.example.hackerton.domain.user.dto.LoginReqDto;
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

    @PostMapping(EndPoint.AUTH_LOGIN)
    public ResponseEntity<?> login(@RequestBody LoginReqDto request) {
        User user = authService.userConfirmation(request.getLoginId(), request.getLoginPw());
        if (user == null || user.getLoginId() == null ) {
            return ResponseEntity.ok(new RejectResponse(CodeSet.AUTH_LOGIN_FAIL));
        }

        LoginTokenResponse loginResponse = authService.createLoginToken(user);

        return ResponseEntity.ok()
                .header("Authorization", loginResponse.getAccessToken())
                .body(new SuccessResponse(CodeSet.OK, loginResponse.getRefreshToken()));
    }

}
