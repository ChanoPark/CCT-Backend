package com.example.hackerton.domain.user.controller;

import com.example.hackerton.domain.user.dto.LoginReqDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthController {
    ResponseEntity<?> userLogin(@RequestBody LoginReqDto request);
}
