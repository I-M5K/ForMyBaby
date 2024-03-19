package com.ssafy.c202.formybaby.baby.repository;

import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse;
import com.ssafy.c202.formybaby.baby.entity.Baby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BabyRepository extends JpaRepository<Baby, Long> {

    @Query("SELECT NEW com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse(\n" +
            "b.babyId,\n" +
            "b.babyName,\n" +
            "b.birthDate,\n" +
            "b.babyGender,\n" +
            "b.profileImg,\n" +
            "f.familyCode\n" +
            ")" +
            "FROM Baby b\n" +
            "left JOIN Family f ON b.babyId = f.baby.babyId\n" +
            "WHERE f.user.userId = :userId")
    List<BabyReadResponse> findBabiesByUserId(Long userId);
    @Query("SELECT NEW com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse(\n" +
            "b.babyId,\n" +
            "b.babyName,\n" +
            "b.birthDate,\n" +
            "b.babyGender,\n" +
            "b.profileImg,\n" +
            "f.familyCode\n" +
            ")" +
            "FROM Baby b\n" +
            "left JOIN Family f ON b.babyId = f.baby.babyId\n" +
            "WHERE f.baby.babyId = :babyId")
    Optional<BabyReadResponse>findBabyByBabyId(Long babyId);

    void deleteByBabyId(Long babyId);
}
