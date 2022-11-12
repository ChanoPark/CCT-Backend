package com.example.hackerton.domain.user.repository;

import com.example.hackerton.domain.user.Store;
import com.example.hackerton.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthStoreRepository extends JpaRepository<Store, Long> {
    @Query("select s from Store s where s.loginId= :id and s.loginPw = :pw") //레벨 검사 필요
    Store login(@Param("id") String id, @Param("pw") String pw);

    @Query("select s from Store s where s.name = :companyName") //레벨 검사 필요
    Store getStore(@Param("companyName") String companyName);
}
