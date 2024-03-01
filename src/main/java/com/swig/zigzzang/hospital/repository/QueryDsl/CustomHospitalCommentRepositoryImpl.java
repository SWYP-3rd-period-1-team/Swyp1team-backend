package com.swig.zigzzang.hospital.repository.QueryDsl;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.swig.zigzzang.hospital.domain.*;
import com.swig.zigzzang.member.domain.QMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@RequiredArgsConstructor
@Repository
public class CustomHospitalCommentRepositoryImpl implements CustomHospitalCommentRepository {

    private final JPAQueryFactory jpaQueryFactory;

    QHospitalComment hospitalComment = QHospitalComment.hospitalComment;
    QMemberHospital memberHospital = QMemberHospital.memberHospital;
    QHospital hospital = QHospital.hospital;
    QMember member = QMember.member;


    @Override
    public List<HospitalComment> findHospitalCommentsByGoogleMapId(String googleMapId) {
        return jpaQueryFactory.selectFrom(hospitalComment)
                .leftJoin(hospitalComment.parent)
                .fetchJoin()
                .where(hospitalComment.hospital.googleMapId.eq(googleMapId))
                .orderBy(
                        hospitalComment.parent.hospitalCommentId.asc().nullsFirst(),
                        hospitalComment.createdDate.asc()
                ).fetch();
    }

    @Override
    public List<Hospital> findHospitalsByUserIdWithBookmark(String userId) {
        return jpaQueryFactory.select(hospital)
                .from(memberHospital)
                .join(memberHospital.hospital, hospital)
                .join(memberHospital.member,member)
                .where(member.userId.eq(userId)
                        .and(memberHospital.bookmark.isTrue()))
                .fetch();

    }
}
