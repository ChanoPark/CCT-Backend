package com.example.hackerton.domain.user.dto;

import com.example.hackerton.domain.user.Store;
import com.example.hackerton.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDto {
    User user;
    Store store;
}
