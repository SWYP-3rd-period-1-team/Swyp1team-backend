package com.swig.zigzzang.calender.controller;


import com.swig.zigzzang.calender.dto.request.SleepScheduleSaveRequest;
import com.swig.zigzzang.calender.dto.response.SleepScheduleSaveResponse;
import com.swig.zigzzang.calender.service.SleepScheduleService;
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
@Tag(name = "SleepScheduleController", description = "캘린더 수면 스케쥴 관련 api")
@RestController
@RequestMapping("/api/calenders/sleepSchedules")
@RequiredArgsConstructor
public class SleepScheduleController {

    private final SleepScheduleService sleepScheduleService;

    @Operation(summary = "캘린더 수면 스케줄 정보 저장 api", description = "캘린더 수면 스케줄 정보를 저장합니다." +
            "sleepPeriod : 24000700-> 24:00~07:00")
    @PostMapping("")
    public HttpResponse<SleepScheduleSaveResponse> sleepScheduleSave(@Valid @RequestBody SleepScheduleSaveRequest sleepScheduleSaveRequest) {

        String loginUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        sleepScheduleService.saveSleepSchedule(loginUserId,sleepScheduleSaveRequest);

        return HttpResponse.okBuild(
                SleepScheduleSaveResponse.of()
        );

    }
}
