package com.ssafy.c202.formybaby.stopmotion.repository;

import com.ssafy.c202.formybaby.stopmotion.entity.Stopmotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StopmotionRepository extends JpaRepository<Stopmotion, Long> {

    Optional<Stopmotion> findByBaby_BabyId(Long babyId);

    int countByBaby_BabyId(Long babyId);
}
