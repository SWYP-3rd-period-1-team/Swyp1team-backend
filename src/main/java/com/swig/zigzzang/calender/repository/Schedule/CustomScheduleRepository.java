package com.swig.zigzzang.calender.repository.Schedule;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.swig.zigzzang.calender.domain.*;
import com.swig.zigzzang.calender.excepiton.UpdateException;
import com.swig.zigzzang.member.domain.QMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class CustomScheduleRepository {

    private final JPAQueryFactory jpaQueryFactory;

    QMember qMember = QMember.member;
    QSchedule qSchedule = QSchedule.schedule;
    QCalender qCalender = QCalender.calender;

    public Optional<Schedule> findByMemberAndCalender(String loginUserId, Long scheduleId) {
        try {
            return Optional.ofNullable(jpaQueryFactory
                    .select(qSchedule)
                    .from(qSchedule)
                    .leftJoin(qSchedule.calender, qCalender)
                    .leftJoin(qCalender.member, qMember)
                    .where(qMember.userId.eq(loginUserId)
                            .and(qSchedule.scheduleId.eq(scheduleId)))
                    .fetchOne());
        } catch (Exception e) {
            throw new UpdateException();
        }
    }


}
