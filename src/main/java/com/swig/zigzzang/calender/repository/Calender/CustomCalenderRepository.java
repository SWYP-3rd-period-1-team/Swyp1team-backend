package com.swig.zigzzang.calender.repository.Calender;


import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.swig.zigzzang.calender.domain.*;
import com.swig.zigzzang.calender.dto.util.*;
import com.swig.zigzzang.calender.excepiton.UpdateException;
import com.swig.zigzzang.member.domain.QMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class CustomCalenderRepository  {

    private final JPAQueryFactory jpaQueryFactory;

    QMember qMember = QMember.member;
    QCalender qCalender = QCalender.calender;
    QSchedule qSchedule = QSchedule.schedule;
    QSleepSchedule qSleepSchedule = QSleepSchedule.sleepSchedule;
    QSupplement qSupplement = QSupplement.supplement;
    QWaterIntake qWaterIntake = QWaterIntake.waterIntake;


    public Optional<Supplement> findByMemberAndCalender(String loginUserId, Long supplementId) {
        try {
            return Optional.ofNullable(jpaQueryFactory
                    .select(qSupplement)
                    .from(qSupplement)
                    .leftJoin(qSupplement.calender, qCalender)
                    .leftJoin(qCalender.member, qMember)
                    .where(qMember.userId.eq(loginUserId)
                            .and(qSupplement.supplementId.eq(supplementId)))
                    .fetchOne());
        } catch (Exception e) {
            throw new UpdateException();
        }
    }
    
    public MyCalenderDTO fetchEntitiesForMemberAndCalender(String calenderDate, Long calenderId) {
        List<Tuple> results = jpaQueryFactory
                .select(qCalender, qSchedule, qSleepSchedule, qSupplement, qWaterIntake)
                .from(qCalender)
                .leftJoin(qSchedule).on(qSchedule.calender.calenderId.eq(qCalender.calenderId)
                        .and(qSchedule.date.eq(calenderDate)))
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
