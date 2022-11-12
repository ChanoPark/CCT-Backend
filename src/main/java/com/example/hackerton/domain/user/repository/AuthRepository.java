package com.example.hackerton.domain.user.repository;

import com.example.hackerton.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<User, Long> {
}
