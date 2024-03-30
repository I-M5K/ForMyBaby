package com.ssafy.c202.formybaby.user.repository;

import com.ssafy.c202.formybaby.user.entity.Family;
import io.lettuce.core.dynamic.annotation.Param;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface FamilyRepository extends JpaRepository<Family, Long> {
    @Query(value = "SELECT DISTINCT f.familyCode FROM Family f WHERE f.baby.babyId = :babyId")
    String findFamilyCodeByBabyId(@Param("babyId") Long babyId);
    @Query(value = "SELECT DISTINCT f.familyCode FROM Family f WHERE f.user.userId = :userId")
    String findFamilyCodeByUserId(@Param("userId") Long userId);
    Family findFamilyByBabyBabyId(Long babyId);
    List<Family> findFamiliesByUserUserId(Long userId);
    @Query("SELECT f.familyRank FROM Family f WHERE f.user.userId=:userId AND f.baby.babyId=:babyId")
    int findFamilyRankByUserId(Long userId, Long babyId);
    @Transactional
    @Modifying
    @Query("UPDATE Family f SET f.familyRank=:rank WHERE f.user.userId=:userId AND f.baby.babyId=:babyId")
    void updateRankByFamilyCode(Long userId, Long babyId, int rank);
    void deleteFamiliesByBabyBabyId(Long babyId);
    @Query("SELECT f FROM Family f WHERE f.familyCode=:familyCode")
    List<Family> findAllByFamilyCode(String familyCode);

    @Query("SELECT f FROM Family f WHERE f.user.userId=:userId")
    List<Family> findAllByUserId(Long userId);

    @Query("SELECT DISTINCT f.user.userId FROM Family f WHERE f.familyCode = :familyCode")
    List<Long> findFirstUserIdByFamilyCode(@Param("familyCode") String familyCode);

}
