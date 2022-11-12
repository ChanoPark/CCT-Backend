package com.example.hackerton.domain.user.repository;

import com.example.hackerton.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthUserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.loginId= :id and u.loginPw = :pw") //레벨 검사 필요
    User login(@Param("id") String id, @Param("pw") String pw);

}
