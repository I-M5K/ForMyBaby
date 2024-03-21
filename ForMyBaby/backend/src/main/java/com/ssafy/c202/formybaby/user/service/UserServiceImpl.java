package com.ssafy.c202.formybaby.user.service;

import com.ssafy.c202.formybaby.user.dto.response.UserReadResponse;
import com.ssafy.c202.formybaby.user.entity.User;
import com.ssafy.c202.formybaby.user.mapper.FamilyMapper;
import com.ssafy.c202.formybaby.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<UserReadResponse> findByUserId(Long userId) {
        User user = userRepository.findByUserId(userId); // User 엔티티를 조회

        if (user != null) {
            UserReadResponse userReadResponse = new UserReadResponse(
                    user.getUserId(),
                    user.getOauth().getName(),
                    user.getOauth().getProfileImg()
            );
            return new ResponseEntity<>(userReadResponse, HttpStatus.OK);
        } else {
            // 해당 userId를 가진 User가 없는 경우 null 반환
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
