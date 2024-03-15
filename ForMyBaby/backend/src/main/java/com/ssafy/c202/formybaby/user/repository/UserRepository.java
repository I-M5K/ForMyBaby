package com.ssafy.c202.formybaby.user.repository;

import com.ssafy.c202.formybaby.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByOauth_Id(Long id);
}
