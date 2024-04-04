package com.ssafy.c202.formybaby.stamp.repository;

import com.ssafy.c202.formybaby.stamp.entity.Stamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StampRepository extends JpaRepository<Stamp,Long>{
    List<Stamp> findByBaby_BabyId(Long babyId);
    Stamp findByStampId(Long stampId);

    @Query("SELECT s FROM Stamp s WHERE s.step = :step AND s.baby.babyId = :babyId")
    Stamp findByStepAndBabyId(Long step, Long babyId);
}
