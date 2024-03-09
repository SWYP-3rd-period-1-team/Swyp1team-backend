package com.swig.zigzzang.calender.repository.QueryDsl;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.swig.zigzzang.hospital.domain.*;
import com.swig.zigzzang.member.domain.QMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@RequiredArgsConstructor
@Repository
public class CustomCalenderRepositoryImpl implements CustomCalenderRepository {

    private final JPAQueryFactory jpaQueryFactory;
    
}
