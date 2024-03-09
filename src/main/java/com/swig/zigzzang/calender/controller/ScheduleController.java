package com.swig.zigzzang.calender.controller;


import com.swig.zigzzang.calender.dto.request.ScheduleSaveRequest;
import com.swig.zigzzang.calender.dto.request.SleepScheduleSaveRequest;
import com.swig.zigzzang.calender.dto.response.ScheduleSaveResponse;
import com.swig.zigzzang.calender.dto.response.SleepScheduleSaveResponse;
import com.swig.zigzzang.calender.service.ScheduleService;
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
@Tag(name = "ScheduleController", description = "캘린더 일정 관련 api")
@RestController
@RequestMapping("/api/calenders/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Operation(summary = "캘린더 일정 정보 저장 api", description = "캘린더 일정 정보를 저장합니다.")
    @PostMapping("")
    public HttpResponse<ScheduleSaveResponse> scheduleSave(@Valid @RequestBody ScheduleSaveRequest scheduleSaveRequest) {

        String loginUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        scheduleService.saveSchedule(loginUserId, scheduleSaveRequest);

        return HttpResponse.okBuild(
                ScheduleSaveResponse.of()
        );

    }
}
