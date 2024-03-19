package com.ssafy.c202.formybaby.user.service;

import com.ssafy.c202.formybaby.user.entity.Family;
import com.ssafy.c202.formybaby.user.entity.User;
import com.ssafy.c202.formybaby.user.mapper.FamilyMapper;
import com.ssafy.c202.formybaby.user.repository.FamilyRepository;
import com.ssafy.c202.formybaby.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final FamilyMapper familyMapper;

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

        // FamilyCode 랜덤생성

        Family family = familyMapper.initFamilyEntity(user.getUserId(), "123");

        return user;
    }
}
