package com.swig.zigzzang.hospital.service;


import com.swig.zigzzang.hospital.domain.Hospital;
import com.swig.zigzzang.hospital.dto.HospitalSaveRequest;
import com.swig.zigzzang.hospital.repository.HospitalRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;


    @Transactional
    public void addHospital(HospitalSaveRequest hospitalSaveRequest) { // 병원 등록

        Hospital hospital = hospitalSaveRequest.toEntity();



    }

}
