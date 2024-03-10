package com.swig.zigzzang.calender.repository.QueryDsl;


import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.swig.zigzzang.calender.domain.*;
import com.swig.zigzzang.calender.dto.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Repository
public class CustomCalenderRepositoryImpl implements CustomCalenderRepository {

    private final JPAQueryFactory jpaQueryFactory;

    QCalender qCalender = QCalender.calender;
    QSchedule qSchedule = QSchedule.schedule;
    QSleepSchedule qSleepSchedule = QSleepSchedule.sleepSchedule;
    QSupplement qSupplement = QSupplement.supplement;
    QWaterIntake qWaterIntake = QWaterIntake.waterIntake;

    @Override
    public MyCalenderDTO fetchEntitiesForMemberAndCalender(String calenderDate, Long calenderId) {
        List<Tuple> results = jpaQueryFactory
                .select(qCalender, qSchedule, qSleepSchedule, qSupplement, qWaterIntake)
                .from(qCalender)
                .leftJoin(qSchedule).on(qSchedule.calender.calenderId.eq(qCalender.calenderId)
                        .and(qSchedule.calenderDate.eq(calenderDate)))
                .leftJoin(qSleepSchedule).on(qSleepSchedule.calender.calenderId.eq(qCalender.calenderId)
                        .and(qSleepSchedule.calenderDate.eq(calenderDate)))
                .leftJoin(qSupplement).on(qSupplement.calender.calenderId.eq(qCalender.calenderId)
                        .and(qSupplement.calenderDate.eq(calenderDate)))
                .leftJoin(qWaterIntake).on(qWaterIntake.calender.calenderId.eq(qCalender.calenderId)
                        .and(qWaterIntake.calenderDate.eq(calenderDate)))
                .where(qCalender.calenderId.eq(calenderId))
                .fetch();

        // 수면스케줄과 물섭취 정보는 단일 존재
        SleepScheduleDTO sleepScheduleDTO = Optional.ofNullable(results.get(0).get(qSleepSchedule))
                .map(SleepScheduleDTO::of)
                .orElse(null);

        WaterIntakeDTO waterIntakeDTO = Optional.ofNullable(results.get(0).get(qWaterIntake))
                .map(WaterIntakeDTO::of)
                .orElse(null);


        // 스케줄 목록과 영양제 목록을 Stream으로 처리하여 DTO 목록을 만듬
        List<ScheduleDTO> scheduleDTOList = results.stream()
                .map(tuple -> tuple.get(qSchedule))
                .filter(Objects::nonNull)
                .distinct()
                .map(ScheduleDTO::of)
                .toList();

        List<SupplementDTO> supplementDTOList = results.stream()
                .map(tuple -> tuple.get(qSupplement))
                .filter(Objects::nonNull)
                .distinct()
                .map(SupplementDTO::of)
                .toList();

        // MyCalenderDTO 객체를 빌드하여 반환합니다.
        return MyCalenderDTO.builder()
                .calenderId(calenderId)
                .sleepScheduleInfo(sleepScheduleDTO)
                .scheduleInfoList(scheduleDTOList)
                .supplementInfoList(supplementDTOList)
                .waterIntakeInfo(waterIntakeDTO)
                .build();
    }

}
