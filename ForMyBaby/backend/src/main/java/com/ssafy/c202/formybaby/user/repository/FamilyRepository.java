package com.ssafy.c202.formybaby.user.repository;

import com.ssafy.c202.formybaby.user.entity.Family;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FamilyRepository extends JpaRepository<Family, Long> {
    @Query(value = "SELECT DISTINCT f.familyCode FROM Family f WHERE f.baby.babyId = :babyId")
    String findFamilyCodeByBabyId(@Param("babyId") Long babyId);

    @Query(value = "SELECT DISTINCT f.familyCode FROM Family f WHERE f.user.userId = :userId")
    String findFamilyCodeByUserId(@Param("userId") Long userId);
    List<Family> findByFamilyId(Long familyId);
    Family findFamilyByUser_UserId(Long userId);
    Family findFamilyByUserUserIdAndBabyBabyId(Long userId, Long babyId);
    Family findFamilyByBabyBabyId(Long babyId);
    Family findFamilyByUserUserIdAndBabyIsNull(Long userId);
    List<Family> findFamiliesByUserUserId(Long userId);
    List<Family> findFamiliesByBabyBabyId(Long babyId);
    List<Family> findByFamilyCode(String familyCode);
    void deleteFamiliesByBabyBabyId(Long babyId);
}
