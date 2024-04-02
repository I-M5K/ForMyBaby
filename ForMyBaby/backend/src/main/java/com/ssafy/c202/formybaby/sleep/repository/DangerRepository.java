package com.ssafy.c202.formybaby.sleep.repository;

import com.ssafy.c202.formybaby.sleep.entity.Danger;
import com.ssafy.c202.formybaby.sleep.entity.Sleep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface DangerRepository extends JpaRepository<Danger, Long> {
    @Query("SELECT d FROM Danger d WHERE d.baby.babyId = ?1 AND d.createdAt BETWEEN ?2 AND ?3 AND d.createdAt = (SELECT MAX(d2.createdAt) FROM Danger d2 WHERE d2.baby.babyId = ?1 AND DATE(d2.createdAt) = DATE(d.createdAt))")
    List<Danger> findByBaby_BabyIdAndCreatedAt(Long babyId, Timestamp startDate, Timestamp endDate);
    @Query("SELECT d FROM Danger d WHERE d.dangerCnt = ?1 AND d.createdAt BETWEEN ?2 AND ?3")
    List<Danger> findByDangerCntAndCreatedAt(int dangerCnt, Timestamp startDate, Timestamp endDate);
    List<Danger> findAllByBaby_BabyIdOrderByCreatedAtDesc(Long babyId);

    @Query("SELECT d FROM Danger d WHERE FUNCTION('DATE', d.createdAt) = :createdDate AND d.baby.babyId = :babyId")
    List<Danger> findAllListByDate(Date createdDate, Long babyId);
}

