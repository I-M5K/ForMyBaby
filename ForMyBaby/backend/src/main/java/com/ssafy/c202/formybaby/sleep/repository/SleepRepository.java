package com.ssafy.c202.formybaby.sleep.repository;

import com.ssafy.c202.formybaby.sleep.entity.Sleep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.sql.Timestamp;
import java.util.List;

public interface SleepRepository extends JpaRepository<Sleep, Long> {
    @Query("SELECT s FROM Sleep s WHERE s.baby.babyId = ?1 AND s.createdAt BETWEEN ?2 AND ?3 AND s.createdAt = (SELECT MAX(s2.createdAt) FROM Sleep s2 WHERE s2.baby.babyId = ?1 AND DATE(s2.createdAt) = DATE(s.createdAt)) ORDER BY s.createdAt DESC")
    List<Sleep> findByWeekSleep(Long babyId, Timestamp startDate, Timestamp endDate);
    List<Sleep> findAllByBaby_BabyIdOrderBySleepIdDesc(Long babyId);
}
