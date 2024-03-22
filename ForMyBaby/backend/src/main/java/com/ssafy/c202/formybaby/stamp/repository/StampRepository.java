package com.ssafy.c202.formybaby.stamp.repository;

import com.ssafy.c202.formybaby.stamp.entity.Stamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StampRepository extends JpaRepository<Stamp,Long>{
}
