package com.ssafy.c202.formybaby.baby.repository;

import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse;
import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse2;
import com.ssafy.c202.formybaby.baby.entity.Baby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BabyRepository extends JpaRepository<Baby, Long> {

    @Query("SELECT DISTINCT NEW com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse(" +
            "b.babyId, " +
            "b.babyName, " +
            "b.birthDate, " +
            "b.babyGender, " +
            "b.profileImg, " +
            "f.familyCode," +
            "f.role" +
            ")" +
            "FROM Baby b " +
            "left JOIN Family f ON b.babyId = f.baby.babyId " +
            "WHERE f.user.userId = :userId GROUP BY b.babyId")
    List<BabyReadResponse> findBabiesByUserId(Long userId);
    @Query("SELECT DISTINCT NEW com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse(" +
            "b.babyId, " +
            "b.babyName, " +
            "b.birthDate, " +
            "b.babyGender, " +
            "b.profileImg, " +
            "f.familyCode," +
            "f.role" +
            ")" +
            "FROM Baby b " +
            "left JOIN Family f ON b.babyId = f.baby.babyId " +
            "WHERE f.familyCode = :familyCode GROUP BY b.babyId")

    List<BabyReadResponse> findBabiesByFamilyCode(String familyCode);

    @Query("SELECT DISTINCT NEW com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse2(" +
            "b.babyId, " +
            "b.babyName, " +
            "b.birthDate, " +
            "b.babyGender, " +
            "b.profileImg, " +
            "f.familyCode, " +
            "f.role" +
            ")" +
            "FROM Baby b " +
            "LEFT JOIN Family f ON b.babyId = f.baby.babyId " +
            "WHERE f.familyCode = :familyCode")
    List<BabyReadResponse2> findBabiesByFamilyCode2(String familyCode);

    @Query("SELECT DISTINCT NEW com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse2(" +
            "b.babyId, " +
            "b.babyName, " +
            "b.birthDate, " +
            "b.babyGender, " +
            "b.profileImg, " +
            "f.familyCode, " +
            "f.role" +
            ")" +
            "FROM Baby b " +
            "left JOIN Family f ON b.babyId = f.baby.babyId " +
            "WHERE f.user.userId = :userId GROUP BY b.babyId")
    List<BabyReadResponse2> findBabiesByUserId2(Long userId);


    @Query("SELECT DISTINCT NEW com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse(" +
            "b.babyId," +
            "b.babyName," +
            "b.birthDate," +
            "b.babyGender," +
            "b.profileImg," +
            "f.familyCode," +
            "f.role" +
            ")" +
            "FROM Baby b " +
            "left JOIN Family f ON b.babyId = f.baby.babyId " +
            "WHERE b.babyId = :babyId GROUP BY b.babyId")
    Optional<BabyReadResponse> findBabyByBabyId(Long babyId);

    Baby findByBabyId(Long babyId);

    void deleteByBabyId(Long babyId);
}
