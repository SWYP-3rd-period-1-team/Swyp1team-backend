package com.swig.zigzzang.calender.repository.Supplement;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.swig.zigzzang.calender.domain.*;
import com.swig.zigzzang.calender.excepiton.UpdateException;
import com.swig.zigzzang.member.domain.QMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class CustomSupplementRepository {

    private final JPAQueryFactory jpaQueryFactory;

    QMember qMember = QMember.member;
    QCalender qCalender = QCalender.calender;
    QSupplement qSupplement = QSupplement.supplement;

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


}
