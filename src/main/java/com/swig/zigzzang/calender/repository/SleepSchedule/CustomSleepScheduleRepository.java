package com.swig.zigzzang.calender.repository.SleepSchedule;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.swig.zigzzang.calender.domain.*;
import com.swig.zigzzang.calender.excepiton.UpdateException;
import com.swig.zigzzang.member.domain.QMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class CustomSleepScheduleRepository {

    private final JPAQueryFactory jpaQueryFactory;

    QMember qMember = QMember.member;
    QCalender qCalender = QCalender.calender;
    QSleepSchedule qSleepSchedule = QSleepSchedule.sleepSchedule;

    public Optional<SleepSchedule> findByMemberAndCalender(String loginUserId, Long sleepScheduleId) {
        try {
            return Optional.ofNullable(jpaQueryFactory
                    .select(qSleepSchedule)
                    .from(qSleepSchedule)
                    .leftJoin(qSleepSchedule.calender, qCalender)
                    .leftJoin(qCalender.member, qMember)
                    .where(qMember.userId.eq(loginUserId)
                            .and(qSleepSchedule.sleepScheduleId.eq(sleepScheduleId)))
                    .fetchOne());
        } catch (Exception e) {
            throw new UpdateException();
        }
    }


}
