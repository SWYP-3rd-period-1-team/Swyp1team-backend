package com.swig.zigzzang.calender.repository.Schedule;

import com.swig.zigzzang.calender.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {


}
