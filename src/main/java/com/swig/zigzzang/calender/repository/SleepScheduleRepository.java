package com.swig.zigzzang.calender.repository;

import com.swig.zigzzang.calender.domain.SleepSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SleepScheduleRepository extends JpaRepository<SleepSchedule, Long> {

}
