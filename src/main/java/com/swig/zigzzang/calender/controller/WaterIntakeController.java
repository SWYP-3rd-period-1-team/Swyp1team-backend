package com.swig.zigzzang.calender.controller;

import com.swig.zigzzang.calender.dto.request.WaterIntakeSaveRequest;
import com.swig.zigzzang.calender.dto.response.WaterIntakeSaveResponse;
import com.swig.zigzzang.calender.service.WaterIntakeService;
import com.swig.zigzzang.global.response.HttpResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@Tag(name = "WaterIntakeController", description = "캘린더 물 섭취 관련 api")
@RestController
@RequestMapping("/api/calenders/waterIntakes")
@RequiredArgsConstructor
public class WaterIntakeController {

    private final WaterIntakeService waterIntakeService;

    @Operation(summary = "물 섭취 정보 저장 api", description = "하루 물 섭취 일정 정보를 저장합니다.")
    @PostMapping("")
    public HttpResponse<WaterIntakeSaveResponse> waterIntakeSave(@Valid @RequestBody WaterIntakeSaveRequest waterIntakeSaveRequest) {

        String loginUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        waterIntakeService.saveWaterIntake(loginUserId,waterIntakeSaveRequest);

        return HttpResponse.okBuild(
                WaterIntakeSaveResponse.of()
        );
    }
}
