package com.example.hackerton.domain.user;

import com.example.hackerton.domain.item.Item;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name="store")
@Entity
public class Store {
    @Id
    @GeneratedValue
    private Long id;

    @Email
    @Column(name = "login_id", nullable = false, unique = true)
    private String loginId;

    @Column(name = "login_pw", nullable = false)
    private String loginPw;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String tel;

    @Column(nullable = false)
    private String address;

    @OneToMany
    @JoinColumn(name = "store_id")
    private List<Item> items = new ArrayList<>();


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserPermission role;

    @Column(nullable = false)
    private String level;
}
