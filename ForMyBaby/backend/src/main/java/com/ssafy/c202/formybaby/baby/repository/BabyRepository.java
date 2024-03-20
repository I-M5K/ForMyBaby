package com.ssafy.c202.formybaby.baby.repository;

import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse;
import com.ssafy.c202.formybaby.baby.entity.Baby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BabyRepository extends JpaRepository<Baby, Long> {

    @Query("SELECT NEW com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse(" +
            "b.babyId, " +
            "b.babyName, " +
            "b.birthDate, " +
            "b.babyGender, " +
            "b.profileImg, " +
            "f.familyCode " +
            ")" +
            "FROM Baby b " +
            "left JOIN Family f ON b.babyId = f.baby.babyId " +
            "WHERE f.user.userId = :userId")
    List<BabyReadResponse> findBabiesByUserId(Long userId);
    // findbyfamilycode
    @Query("SELECT NEW com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse(" +
            "b.babyId, " +
            "b.babyName, " +
            "b.birthDate, " +
            "b.babyGender, " +
            "b.profileImg, " +
            "f.familyCode " +
            ")" +
            "FROM Baby b " +
            "left JOIN Family f ON b.babyId = f.baby.babyId " +
            "WHERE f.familyCode = :familyCode")
    List<BabyReadResponse> findBabiesByFamilyCode(String familyCode);
    @Query("SELECT NEW com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse(" +
            "b.babyId," +
            "b.babyName," +
            "b.birthDate," +
            "b.babyGender," +
            "b.profileImg," +
            "f.familyCode" +
            ")" +
            "FROM Baby b " +
            "left JOIN Family f ON b.babyId = f.baby.babyId " +
            "WHERE f.baby.babyId = :babyId")
    Optional<BabyReadResponse>findBabyByBabyId(Long babyId);

    void deleteByBabyId(Long babyId);
}
