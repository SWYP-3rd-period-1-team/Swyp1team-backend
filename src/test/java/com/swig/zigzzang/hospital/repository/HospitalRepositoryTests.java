package com.swig.zigzzang.hospital.repository;


import com.swig.zigzzang.global.P6Spy.P6SpyConfig;
import com.swig.zigzzang.hospital.domain.Hospital;
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
public class HospitalRepositoryTests {

    @Autowired
    private HospitalRepository hospitalRepository;

    private static Hospital hospital;


    @BeforeEach // 테스트 실행 전 매번 실행
    void setup() {
        hospital = Hospital.builder()
                .googleMapId(1000L)
                .build();
    }

    @Test
    @DisplayName("병원 저장 repository test")
    public void saveHospitalTest() {
        // given
        // static hospital 사용

        // when
        Hospital saveHospital = hospitalRepository.save(hospital);

        // then
        assertThat(saveHospital).isNotNull();
        assertThat(saveHospital.getGoogleMapId()).isEqualTo(hospital.getGoogleMapId());
    }


}
