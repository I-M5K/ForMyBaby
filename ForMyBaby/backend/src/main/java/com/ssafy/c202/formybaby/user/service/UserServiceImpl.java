package com.ssafy.c202.formybaby.user.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.TopicManagementResponse;
import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.baby.repository.BabyRepository;
import com.ssafy.c202.formybaby.fcm.service.FCMService;
import com.ssafy.c202.formybaby.global.jpaEnum.Role;
import com.ssafy.c202.formybaby.global.redis.LatLon;
import com.ssafy.c202.formybaby.global.redis.RedisService;
import com.ssafy.c202.formybaby.notification.entity.Notification;
import com.ssafy.c202.formybaby.notification.repository.NotificationRepository;
import com.ssafy.c202.formybaby.user.dto.request.UserUpdateRequest;
import com.ssafy.c202.formybaby.user.dto.response.UserProfileResponse;
import com.ssafy.c202.formybaby.user.dto.response.UserReadResponse;
import com.ssafy.c202.formybaby.user.entity.Family;
import com.ssafy.c202.formybaby.user.entity.Oauth;
import com.ssafy.c202.formybaby.user.entity.User;
import com.ssafy.c202.formybaby.user.repository.FamilyRepository;
import com.ssafy.c202.formybaby.user.repository.OauthRepository;
import com.ssafy.c202.formybaby.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final RedisService redisService;

    private final OauthRepository oauthRepository;

    private final FamilyRepository familyRepository;

    private final BabyRepository babyRepository;

    private final FCMService fcmService;

    private final NotificationRepository notificationRepository;


    @Override
    public UserReadResponse findUser(Long userId) {
        log.info("findUser");
        User user = userRepository.findByUserId(userId);
        log.info("user : " + user);
        if (user != null) {
            Oauth oauth = oauthRepository.findByOauthId(user.getOauth().getOauthId());
            log.info("oauth :" + oauth);
            List<Family> family = familyRepository.findFamiliesByUserUserId(userId);
            Family firstFamily = family.get(0);
            log.info("firstFamily :" + firstFamily);
            UserReadResponse userReadResponse = new UserReadResponse(
                    user.getUserId(),
                    oauth.getName(),
                    oauth.getProfileImg(),
                    family != null ? firstFamily.getRole() : null
            );
            log.info("userReadResponse : " + userReadResponse);
            return userReadResponse;
        } else {
            return null;
        }
    }
    @Override
    public User findByOauthId(Long oauthId) {
        // 사용자를 찾음
        User user = userRepository.findByOauth_OauthId(oauthId);
        // 사용자가 없는 경우
        if (user == null) {
            // 예외를 발생시킴
            throw new RuntimeException("User not found for oauthId: " + oauthId);
        }
        // 사용자를 반환
        return user;
    }


    @Override
    public User registerUser(User user) {
        // 사용자가 이미 데이터베이스에 존재하는지 확인
        User existingUser = userRepository.findByOauth_OauthId(user.getOauth().getOauthId());
        if (existingUser != null) {
            // 이미 존재하는 사용자이므로 예외를 발생시킴
            throw new RuntimeException("User already exists");
        }
        // 사용자를 등록하고 저장
        user = userRepository.save(user);
        return user;
    }

//    @Override
//    public void deleteUser(String token) {
//        String redisGetUserId = redisService.getUserIdByToken(token);
//        // Redis에서 가져온 값이 null이 아니고, 길이가 충분히 길다면
//        if (redisGetUserId != null && redisGetUserId.length() >= 11) {
//            // 마지막 11자리를 제외한 나머지를 id로 추정
//            String idSubstring = redisGetUserId.substring(0, redisGetUserId.length() - 11);
//            log.info("idSubstring : " + idSubstring);
//            long userId = Long.parseLong(idSubstring);
//            // userId를 사용하여 필요한 작업을 수행
//            userRepository.deleteById(userId);
//        } else {
//            log.error("Redis에서 올바른 userId 값을 가져오지 못했습니다.");
//        }
//    }
    @Override
    public void deleteUser(Long userId) {
        // 삭제할 사용자를 조회합니다.
        User user = userRepository.findByUserId(userId);

        Oauth oauth = oauthRepository.findByOauthId(user.getOauth().getOauthId());

        List<Family> familyList = familyRepository.findFamiliesByUserUserId(user.getUserId());

        List<Notification> notificationList = notificationRepository.findAllByUserUserId(user.getUserId());

        // 사용자가 존재하는 경우에만 삭제를 진행합니다.
        if (user != null) {
            // 연관된 다른 엔티티들도 함께 삭제할 필요가 있다면 여기에서 처리합니다.

            // oauth 엔티티 삭제
            oauthRepository.delete(oauth);

            // 만들어진 패밀리리스트 삭제
            for(Family family : familyList){
                familyRepository.delete(family);
            }

            // 알림목록도 삭제하는 로직 추가해야함
            for(Notification notification : notificationList){
                notificationRepository.delete(notification);
            }

            // 사용자를 삭제합니다.
            userRepository.delete(user);
        } else {
            // 사용자가 존재하지 않는 경우 예외를 발생시킵니다.
            throw new RuntimeException("User not found for userId: " + userId);
        }
    }


    @Override
    public UserProfileResponse updateUser(UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findByUserId(userUpdateRequest.userId());
        log.info("user : " + user);
        Oauth oauth = oauthRepository.findByOauthId(user.getOauth().getOauthId());
        log.info("oauth : " + user);
        List<Family> familyList = familyRepository.findFamiliesByUserUserId(userUpdateRequest.userId());
        log.info("familyList : " + familyList);
        Role role = userUpdateRequest.role();
        log.info("role : " + role);

        if (user != null) {
            // 패밀리 목록도 업데이트 필요
            for (Family family : familyList) {
                Family updatedFamily = new Family(); // 새로운 Family 객체 생성
                Baby baby = babyRepository.findByBabyId(family.getBaby().getBabyId());
                log.info("baby : " + baby);
                updatedFamily.setFamilyCode(family.getFamilyCode()); // 패밀리 코드 설정
                updatedFamily.setFamilyRank(family.getFamilyRank()); // 패밀리 랭크 설정
                updatedFamily.setUser(user); // 해당 유저와 연결
                updatedFamily.setRole(role); // 새로운 역할 설정
                updatedFamily.setFamilyId(family.getFamilyId()); // 패밀리 아이디 설정
                updatedFamily.setBaby(baby); // 아이 정보 설정
                log.info("updatedFamily : " + updatedFamily);
                familyRepository.save(updatedFamily); // 엔티티 저장
            }
            // oauth에서도 프로필이랑 이름 업데이트
            oauth.setName(userUpdateRequest.name());
            oauth.setProfileImg(userUpdateRequest.profileImg().get(0)); // 사진 리스트 중 처음 껄로
            log.info("oauth : " + oauth);
            oauthRepository.save(oauth);

            UserProfileResponse userProfileResponse = new UserProfileResponse(
                    oauth.getName(),
                    oauth.getProfileImg(),
                    role
            );

            log.info("userProfileResponse : " + userProfileResponse);

            return userProfileResponse;
        } else {
            return null;
        }
    }
    @Override
    public String findFcmToken(Long userId) {
        try{
            String fcmToken = userRepository.findFcmTokenByUserId(userId);
            return fcmToken;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void setFCMToken(Long userId, String fcmToken) {
        userRepository.updateFcmTokenByUserId(userId, fcmToken);
    }

    @Override
    public void setLocation(Long userId, double lat, double lon) {
        String familyCode = familyRepository.findFamilyCodeByUserId(userId);
        Map<String, LatLon> map = redisService.setLocation(familyCode, userId, lat, lon);
        String babyId = redisService.getBabyIdByToken(String.valueOf(userId));
//        LatLon baby = map.remove(babyId);
        LatLon baby = new LatLon(35.1483188, 126.9291854);
        for (String id : map.keySet()) {
            LatLon loc = map.get(id);
            double dist = baby.getDistance(loc);
            int oldRank = familyRepository.findFamilyRankByUserId(Long.valueOf(id));
            String token = userRepository.findFcmTokenByUserId(Long.valueOf(id));
            if(dist <= 0.5) {
                familyRepository.updateRankByFamilyCode(Long.valueOf(id), Long.valueOf(babyId), 1);
                try {
                    fcmService.unsubscribe(token, familyCode+"_"+babyId+"_"+oldRank);
                    log.info("topic UNsubscribe: {}", familyCode+"_"+babyId+"_"+oldRank);
                } catch (FirebaseMessagingException e) {
                    throw new RuntimeException(e);
                }
                try {
                    fcmService.subscribe(token, familyCode+"_"+babyId+"_"+1);
                    log.info("topic subscribe: {}", familyCode+"_"+babyId+"_"+1);
                } catch (FirebaseMessagingException e) {
                    throw new RuntimeException(e);
                }
            } else if (dist <= 2) {
                familyRepository.updateRankByFamilyCode(Long.valueOf(id), Long.valueOf(babyId), 2);
                try {
                    fcmService.unsubscribe(token, familyCode+"_"+babyId+"_"+oldRank);
                    log.info("topic UNsubscribe: {}", familyCode+"_"+babyId+"_"+oldRank);
                } catch (FirebaseMessagingException e) {
                    throw new RuntimeException(e);
                }
                try {
                    fcmService.subscribe(token, familyCode+"_"+babyId+"_"+2);
                    log.info("topic subscribe: {}", familyCode+"_"+babyId+"_"+2);
                } catch (FirebaseMessagingException e) {
                    throw new RuntimeException(e);
                }
            } else {
                familyRepository.updateRankByFamilyCode(Long.valueOf(id), Long.valueOf(babyId), 3);
                try {
                    fcmService.unsubscribe(token, familyCode+"_"+babyId+"_"+oldRank);
                    log.info("topic UNsubscribe: {}", familyCode+"_"+babyId+"_"+oldRank);
                } catch (FirebaseMessagingException e) {
                    throw new RuntimeException(e);
                }
                try {
                    fcmService.subscribe(token, familyCode+"_"+babyId+"_"+3);
                    log.info("topic subscribe: {}", familyCode+"_"+babyId+"_"+3);
                } catch (FirebaseMessagingException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
