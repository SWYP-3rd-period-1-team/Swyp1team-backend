package com.swig.zigzzang.calender.repository.WaterIntake;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.swig.zigzzang.calender.domain.*;
import com.swig.zigzzang.calender.excepiton.UpdateException;
import com.swig.zigzzang.member.domain.QMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class CustomWaterIntakeRepository {

    private final JPAQueryFactory jpaQueryFactory;

    QMember qMember = QMember.member;
    QCalender qCalender = QCalender.calender;
    QWaterIntake qWaterIntake = QWaterIntake.waterIntake;

    public Optional<WaterIntake> findByMemberAndCalender(String loginUserId, Long waterIntakeId) {
        try {
            return Optional.ofNullable(jpaQueryFactory
                    .select(qWaterIntake)
                    .from(qWaterIntake)
                    .leftJoin(qWaterIntake.calender, qCalender)
                    .leftJoin(qCalender.member, qMember)
                    .where(qMember.userId.eq(loginUserId)
                            .and(qWaterIntake.waterIntakeId.eq(waterIntakeId)))
                    .fetchOne());
        } catch (Exception e) {
            throw new UpdateException();
        }
    }


}
