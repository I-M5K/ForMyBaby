package com.ssafy.c202.formybaby.user.service;

import com.ssafy.c202.formybaby.user.entity.User;
import com.ssafy.c202.formybaby.user.mapper.FamilyMapper;
import com.ssafy.c202.formybaby.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public User findByUserId(Long userId) {
        User user = userRepository.findByUserId(userId);
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }
    @Override
    public User registerUser(User user) {
        log.info("user : " + user);
        user = userRepository.save(user);
        return user;
    }
}
