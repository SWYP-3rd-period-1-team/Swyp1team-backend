package com.swig.zigzzang.calender.repository.SleepSchedule;

import com.swig.zigzzang.calender.domain.Calender;
import com.swig.zigzzang.calender.domain.SleepSchedule;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SleepScheduleRepository extends JpaRepository<SleepSchedule, Long> {
    Optional<SleepSchedule> findByCalender(Calender calender);

}
