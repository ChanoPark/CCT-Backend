package com.example.hackerton.domain.user;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name="user")
@Entity
public class User {
    @Id @GeneratedValue
    private Long id;

    @Email
    @Column(name = "login_id", nullable = false, unique = true)
    private String loginId;

    @Column(name = "login_pw", nullable = false)
    private String loginPw;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String tel;

    @Column(nullable = false)
    private UserPermission role;

    @Column(nullable = false)
    private String level;
}
