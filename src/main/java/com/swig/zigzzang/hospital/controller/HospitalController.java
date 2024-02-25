package com.swig.zigzzang.hospital.controller;


import com.swig.zigzzang.hospital.service.HospitalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@Tag(name = "HospitalController",description = "병원 관련 api")
@RestController
@RequestMapping("/api/hospitals")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

   /* @Operation(summary = "병원 등록 api",description = "회원 정보 및 카카오맵 ID를 바탕으로 병원 정보를 데이터 베이스에 저장합니다.")
    @PostMapping*/



}
