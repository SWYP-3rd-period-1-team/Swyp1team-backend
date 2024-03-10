package com.swig.zigzzang.calender.controller;


import com.swig.zigzzang.calender.dto.request.SleepSchedule.SleepScheduleSaveRequest;
import com.swig.zigzzang.calender.dto.request.SleepSchedule.SleepScheduleUpdateRequest;
import com.swig.zigzzang.calender.dto.request.Supplement.SupplementUpdateRequest;
import com.swig.zigzzang.calender.dto.response.DeleteResponse;
import com.swig.zigzzang.calender.dto.response.SaveResponse;
import com.swig.zigzzang.calender.dto.response.UpdateResponse;
import com.swig.zigzzang.calender.service.SleepScheduleService;
import com.swig.zigzzang.global.response.HttpResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Tag(name = "SleepScheduleController", description = "캘린더 수면 스케쥴 관련 api")
@RestController
@RequestMapping("/api/calenders/sleepSchedules")
@RequiredArgsConstructor
public class SleepScheduleController {

    private final SleepScheduleService sleepScheduleService;

    @Operation(summary = "캘린더 수면 정보 저장 api", description = "날짜별 캘린더 수면 스케줄 정보를 저장합니다." +
            "sleepPeriod : 24000700-> 24:00~07:00")
    @PostMapping("")
    public HttpResponse<SaveResponse> sleepScheduleSave(@Valid @RequestBody SleepScheduleSaveRequest sleepScheduleSaveRequest) {

        String loginUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        sleepScheduleService.saveSleepSchedule(loginUserId,sleepScheduleSaveRequest);

        return HttpResponse.okBuild(
                SaveResponse.of()
        );

    }

    @Operation(summary = "캘린더 수면 정보 수정 api", description = "날짜별 수면 정보를 수정합니다.")
    @PutMapping("/{sleepScheduleId}")
    public HttpResponse<UpdateResponse> sleepScheduleModify(@Valid @RequestBody SleepScheduleUpdateRequest sleepScheduleUpdateRequest,
                                                         @PathVariable Long sleepScheduleId) {

        String loginUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        sleepScheduleService.modifySleepSchedule(loginUserId,sleepScheduleId,sleepScheduleUpdateRequest);

        return HttpResponse.okBuild(
                UpdateResponse.of()
        );

    }

    @Operation(summary = "캘린더 수면 정보 삭제 api", description = "날짜별 캘린더 수면 정보를 삭제합니다.")
    @DeleteMapping("/{sleepScheduleId}")
    public HttpResponse<DeleteResponse> sleepScheduleRemove(@PathVariable Long sleepScheduleId) {

        String loginUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        sleepScheduleService.removeSleepSchedule(loginUserId,sleepScheduleId);

        return HttpResponse.okBuild(
                DeleteResponse.of()
        );

    }
}
