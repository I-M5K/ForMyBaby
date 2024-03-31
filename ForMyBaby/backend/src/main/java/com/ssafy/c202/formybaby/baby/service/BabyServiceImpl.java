package com.ssafy.c202.formybaby.baby.service;

import com.ssafy.c202.formybaby.baby.dto.request.BabyCreateListRequest;
import com.ssafy.c202.formybaby.baby.dto.request.BabyCreateRequest;
import com.ssafy.c202.formybaby.baby.dto.request.BabyUpdateRequest;
import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse;
import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse2;
import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.baby.mapper.BabyMapper;
import com.ssafy.c202.formybaby.baby.repository.BabyRepository;
import com.ssafy.c202.formybaby.global.redis.RedisService;
import com.ssafy.c202.formybaby.global.s3.AwsS3Service;
import com.ssafy.c202.formybaby.notification.repository.NotificationRepository;
import com.ssafy.c202.formybaby.user.dto.response.FamilyReadResponse;
import com.ssafy.c202.formybaby.user.entity.Family;
import com.ssafy.c202.formybaby.user.entity.User;
import com.ssafy.c202.formybaby.user.mapper.FamilyMapper;
import com.ssafy.c202.formybaby.user.repository.FamilyRepository;
import com.ssafy.c202.formybaby.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BabyServiceImpl implements BabyService{

    private final BabyRepository babyRepository;
    private final FamilyRepository familyRepository;
    private final UserRepository userRepository;
    private final BabyMapper babyMapper;
    private final FamilyMapper familyMapper;
    private final RedisService redisService;
    private final AwsS3Service awsS3Service;
    private final NotificationRepository notificationRepository;
    @Override
    public List<BabyReadResponse> addBaby(BabyCreateRequest babyCreateRequest) {
        User user = userRepository.findByUserId(babyCreateRequest.userId());
        Baby baby = babyMapper.toBabyEntity(babyCreateRequest);
        Timestamp timestamp = getCurrentTimestamp();
        babyRepository.save(baby);
        String uploadFileName = awsS3Service.uploadFile(baby.getBabyId(),babyCreateRequest.files(),timestamp,"pro");
        baby.setProfileImg(uploadFileName);
        babyRepository.save(baby);
        String familyCode = familyRepository.findFamilyCodeByUserId(babyCreateRequest.userId());
        Family family = familyMapper.initFamilyEntity(user, baby, babyCreateRequest, familyCode,1 );
        familyRepository.save(family);
        log.info("{}", babyRepository.findBabiesByUserId(user.getUserId()));
        return babyRepository.findBabiesByUserId(user.getUserId());
    }

    public void createNewBaby(List<BabyCreateRequest> babyCreateRequestList) {
        User user = userRepository.findByUserId(babyCreateRequestList.get(0).userId());
        String familyCode = RandomStringUtils.randomAlphanumeric(6);
        for(BabyCreateRequest babyCreateRequest : babyCreateRequestList){
            Baby baby = babyMapper.toBabyEntity(babyCreateRequest);
            babyRepository.save(baby);

            Family family = familyMapper.initFamilyEntity(user, baby, babyCreateRequest, familyCode, 1);
            familyRepository.save(family);
        }
    }
    public FamilyReadResponse createNewBabyNoShareCode(String token, BabyCreateRequest babyCreateRequest) {
        User user = userRepository.findByUserId(babyCreateRequest.userId());
        log.info("User : " + user);
        Baby baby = babyMapper.toBabyEntity(babyCreateRequest);
        log.info("Baby : " + baby);
        String familyCode = RandomStringUtils.randomAlphanumeric(6);
        log.info("familyCode : " + familyCode);

        Timestamp timestamp = getCurrentTimestamp();

        babyRepository.save(baby);
        String uploadFileName = awsS3Service.uploadFile(baby.getBabyId(),babyCreateRequest.files(),timestamp, "pro");
        baby.setProfileImg(uploadFileName);
        babyRepository.save(baby);
        Family family = familyMapper.initFamilyEntity(user, baby, babyCreateRequest, familyCode, 1);
        familyRepository.save(family);

        List<BabyReadResponse> babyReadResponseList = new ArrayList<>();

        BabyReadResponse babyReadResponse = new BabyReadResponse(
                baby.getBabyId(),
                baby.getBabyName(),
                baby.getBirthDate(),
                baby.getBabyGender(),
                baby.getProfileImg(),
                family.getFamilyCode(),
                family.getRole()
        );

        babyReadResponseList.add(babyReadResponse);

        log.info("babyReadResponseList :" + babyReadResponseList);

        //새로운 아이 등록 시 아이 번호 레디스에 저장.
        redisService.saveBabyIdsByToken(redisService.getUserIdByToken(token), baby.getBabyId());

        FamilyReadResponse familyReadResponse = new FamilyReadResponse(babyReadResponseList);
        log.info("familyReadResponse : " + familyReadResponse);
        return familyReadResponse;
    }

    @Override
    public BabyReadResponse updateBaby(BabyUpdateRequest babyUpdateRequest) {
        Baby baby = babyMapper.toBabyEntity(babyUpdateRequest);
        babyMapper.updateBabyFromRequest(babyUpdateRequest, baby);

        babyRepository.save(baby);

        return babyRepository.findBabyByBabyId(baby.getBabyId())
                .orElseThrow(NullPointerException::new);
    }

    @Override
    public BabyReadResponse babyDetail(Long babyId) {
        return babyRepository.findBabyByBabyId(babyId).orElseThrow(NullPointerException::new);
    }

    @Override
    public List<BabyReadResponse> babyList(Long userId) {
        return babyRepository.findBabiesByUserId(userId);
    }
    @Override
    public List<BabyReadResponse> babyList(String familyCode) {
        return babyRepository.findBabiesByFamilyCode(familyCode);
    }

    @Override
    public List<BabyReadResponse2> babyList2(String familyCode) {
        return babyRepository.findBabiesByFamilyCode2(familyCode);
    }

    @Override
    public List<BabyReadResponse2> babyList3(Long userId) {
        return babyRepository.findBabiesByUserId2(userId);
    }

    @Override
    public void deleteBaby(Long babyId) {
        notificationRepository.deleteAllByBabyId(babyId);
        familyRepository.deleteFamiliesByBabyBabyId(babyId);
        babyRepository.deleteByBabyId(babyId);
    }

    // 현재 시간을 Timestamp 객체로 가져오는 메서드
    public Timestamp getCurrentTimestamp() {
        // 현재 시간을 밀리초로 가져옴
        long currentTimeMillis = System.currentTimeMillis();
        // 밀리초를 Timestamp 객체로 변환하여 반환
        return new Timestamp(currentTimeMillis);
    }
}
