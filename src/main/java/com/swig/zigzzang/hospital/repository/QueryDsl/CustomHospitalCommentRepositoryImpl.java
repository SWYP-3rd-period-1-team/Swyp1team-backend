package com.swig.zigzzang.hospital.repository.QueryDsl;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.swig.zigzzang.hospital.domain.HospitalComment;
import com.swig.zigzzang.hospital.domain.QHospitalComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@RequiredArgsConstructor
@Repository
public class CustomHospitalCommentRepositoryImpl implements CustomHospitalCommentRepository {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<HospitalComment> findHospitalCommentsByGoogleMapId(String googleMapId) {
        return jpaQueryFactory.selectFrom(QHospitalComment.hospitalComment)
                .leftJoin(QHospitalComment.hospitalComment.parent)
                .fetchJoin()
                .where(QHospitalComment.hospitalComment.hospital.googleMapId.eq(googleMapId))
                .orderBy(
                        QHospitalComment.hospitalComment.parent.hospitalCommentId.asc().nullsFirst(),
                        QHospitalComment.hospitalComment.createdDate.asc()
                ).fetch();
    }
}
