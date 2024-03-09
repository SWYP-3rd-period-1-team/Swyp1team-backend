package com.swig.zigzzang.calender.repository;

import com.swig.zigzzang.calender.domain.WaterIntake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterIntakeRepository extends JpaRepository<WaterIntake,Long> {
}
