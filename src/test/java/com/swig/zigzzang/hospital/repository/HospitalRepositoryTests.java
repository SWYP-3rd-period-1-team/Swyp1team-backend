package com.swig.zigzzang.hospital.repository;


import com.swig.zigzzang.global.P6Spy.P6SpyConfig;
import com.swig.zigzzang.hospital.domain.Hospital;
import com.swig.zigzzang.hospital.domain.HospitalComment;
import com.swig.zigzzang.hospital.domain.MemberHospital;
import com.swig.zigzzang.member.domain.Member;
import com.swig.zigzzang.member.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest(showSql = false)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(P6SpyConfig.class)
@Log4j2
public class HospitalRepositoryTests {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberHospitalRepository memberHospitalRepository;

    @Autowired
    private HospitalCommentRepository hospitalCommentRepository;

    private static Hospital hospital;

    private static MemberHospital memberHospital;

    private static Member member;


    @BeforeEach   // 테스트 실행 전 매번 실행
    void setup() {

        member = Member.builder()
                .memberId(1L)
                .userId("testUser")
                .email("test")
                .nickname("test")
                .password("test")
                .build();

        hospital = Hospital.builder()
                .googleMapId("abcd")
                .build();

        memberHospital = MemberHospital.builder()
                .member(member)
                .hospital(hospital)
                .bookmark(true)
                .build();

        memberRepository.save(member); // test member 저장
        hospitalRepository.save(hospital);
        memberHospitalRepository.save(memberHospital);
    }

    @Test
    @DisplayName("북마크 등록 Test")
    void insertHospital() {

        // given
        memberRepository.save(member);

        // static hospital 사용

        // when
        Hospital saveHospital = hospitalRepository.save(hospital);
        MemberHospital saveMemberHospital = memberHospitalRepository.save(memberHospital);

        // then
        assertThat(saveHospital).isNotNull();
        assertThat(saveMemberHospital).isNotNull();
    }


    @Test
    @DisplayName("댓글 등록 Test")
    void insertComment() {

        // given
        HospitalComment hospitalComment = HospitalComment.builder()
                .hospital(hospital)
                .member(member)
                .content("댓글 test")
                .star(5L)
                .isDeleted(false)
                .build();

        // when
        HospitalComment saveHospitalComment = hospitalCommentRepository.save(hospitalComment);

        // then
        assertThat(saveHospitalComment).isNotNull();
    }


}
