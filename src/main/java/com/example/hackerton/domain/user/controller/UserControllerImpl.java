package com.example.hackerton.domain.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserController {

    @GetMapping("/user")
    public String test() {
        return "Sync TEST";
    }

}
