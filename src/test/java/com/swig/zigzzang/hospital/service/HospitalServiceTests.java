package com.swig.zigzzang.hospital.service;


import com.swig.zigzzang.global.P6Spy.P6SpyConfig;
import com.swig.zigzzang.hospital.repository.HospitalCommentRepository;
import com.swig.zigzzang.hospital.repository.HospitalRepository;
import com.swig.zigzzang.member.domain.Member;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Import(P6SpyConfig.class)
public class HospitalServiceTests {

    @Mock
    private HospitalRepository hospitalRepository; // 가짜  HospitalRepository 객체 생성

    @Mock
    private HospitalCommentRepository hospitalCommentRepository;

    @InjectMocks
    private HospitalService hospitalService;

    private static Member member;

    @BeforeAll
    static void setup() {

    }


    // TODO : 병원 등록 관련
    @Test
    @DisplayName("병원 댓글 등록")
    void saveHospitalBookmarkTest() {

        // given


        // when


        // then
    }





}
