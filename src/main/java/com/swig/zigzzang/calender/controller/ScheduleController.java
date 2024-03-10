package com.swig.zigzzang.calender.controller;


import com.swig.zigzzang.calender.dto.request.Schedule.ScheduleSaveRequest;
import com.swig.zigzzang.calender.dto.request.Schedule.ScheduleUpdateRequest;
import com.swig.zigzzang.calender.dto.request.Supplement.SupplementUpdateRequest;
import com.swig.zigzzang.calender.dto.response.DeleteResponse;
import com.swig.zigzzang.calender.dto.response.SaveResponse;
import com.swig.zigzzang.calender.dto.response.UpdateResponse;
import com.swig.zigzzang.calender.service.ScheduleService;
import com.swig.zigzzang.global.response.HttpResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Tag(name = "ScheduleController", description = "캘린더 일정 관련 api")
@RestController
@RequestMapping("/api/calenders/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Operation(summary = "캘린더 일정 정보 저장 api", description = "날짜별 캘린더 일정 정보를 저장합니다.")
    @PostMapping("")
    public HttpResponse<SaveResponse> scheduleSave(@Valid @RequestBody ScheduleSaveRequest scheduleSaveRequest) {

        String loginUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        scheduleService.saveSchedule(loginUserId, scheduleSaveRequest);

        return HttpResponse.okBuild(
                SaveResponse.of()
        );

    }

    @Operation(summary = "캘린더 일정 정보 수정 api", description = "날짜별 캘린더 일정 정보를 수정합니다.")
    @PutMapping("/{scheduleId}")
    public HttpResponse<UpdateResponse> scheduleModify(@Valid @RequestBody ScheduleUpdateRequest scheduleUpdateRequest,
                                                         @PathVariable Long scheduleId) {

        String loginUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        scheduleService.modifySchedule(loginUserId,scheduleId,scheduleUpdateRequest);

        return HttpResponse.okBuild(
                UpdateResponse.of()
        );

    }

    @Operation(summary = "캘린더 일정 정보 삭제 api", description = "날짜별 캘린더 일정 정보를 삭제합니다.")
    @DeleteMapping("/{scheduleId}")
    public HttpResponse<DeleteResponse> scheduleRemove(@PathVariable Long scheduleId) {

        String loginUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        scheduleService.removeSchedule(loginUserId,scheduleId);

        return HttpResponse.okBuild(
                DeleteResponse.of()
        );

    }
}
