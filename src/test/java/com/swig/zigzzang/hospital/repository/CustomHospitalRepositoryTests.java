package com.swig.zigzzang.hospital.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.swig.zigzzang.hospital.domain.HospitalComment;
import com.swig.zigzzang.hospital.domain.QHospitalComment;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Log4j2
public class CustomHospitalRepositoryTests {


    @Autowired
    JPAQueryFactory jpaQueryFactory;


    @Test
    @DisplayName("병원 상세 조회")
    public void findHospitalCommentsByGoogleMapIdTest() {
        // given
        String googleMapId = "ChIJgUbEo8cfqokR5lP9_Wh_DaM";

        // when
        List<HospitalComment> result = jpaQueryFactory.selectFrom(QHospitalComment.hospitalComment)
                .leftJoin(QHospitalComment.hospitalComment.parent)
                .fetchJoin()
                .where(QHospitalComment.hospitalComment.hospital.googleMapId.eq(googleMapId))
                .orderBy(
                        QHospitalComment.hospitalComment.parent.hospitalCommentId.asc().nullsFirst(),
                        QHospitalComment.hospitalComment.createDate.asc()
                ).fetch();

        // then
        result.forEach(hospitalComment -> log.info(result.toString()));
        assertThat(result).isNotNull();
    }

}
