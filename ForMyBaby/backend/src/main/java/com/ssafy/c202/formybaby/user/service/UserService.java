package com.ssafy.c202.formybaby.user.service;

import com.ssafy.c202.formybaby.user.entity.User;
import org.springframework.stereotype.Service;

public interface UserService {
   User findByUserId(Long id);
   User registerUser(User user);
}
