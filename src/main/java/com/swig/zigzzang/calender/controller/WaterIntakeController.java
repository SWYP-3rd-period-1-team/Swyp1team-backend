package com.swig.zigzzang.calender.controller;

import com.swig.zigzzang.calender.dto.request.Supplement.SupplementAchieveUpdateRequest;
import com.swig.zigzzang.calender.dto.request.Supplement.SupplementUpdateRequest;
import com.swig.zigzzang.calender.dto.request.WaterIntake.WaterIntakeAchieveUpdateRequest;
import com.swig.zigzzang.calender.dto.request.WaterIntake.WaterIntakeSaveRequest;
import com.swig.zigzzang.calender.dto.request.WaterIntake.WaterIntakeUpdateRequest;
import com.swig.zigzzang.calender.dto.response.DeleteResponse;
import com.swig.zigzzang.calender.dto.response.SaveResponse;
import com.swig.zigzzang.calender.dto.response.UpdateResponse;
import com.swig.zigzzang.calender.service.WaterIntakeService;
import com.swig.zigzzang.global.response.HttpResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Tag(name = "WaterIntakeController", description = "캘린더 물 섭취 관련 api")
@RestController
@RequestMapping("/api/calenders/waterIntakes")
@RequiredArgsConstructor
public class WaterIntakeController {

    private final WaterIntakeService waterIntakeService;

    @Operation(summary = "캘린더 물 섭취 정보 저장 api", description = "날짜별 하루 물 섭취 일정 정보를 저장합니다.")
    @PostMapping("")
    public HttpResponse<SaveResponse> waterIntakeSave(@Valid @RequestBody WaterIntakeSaveRequest waterIntakeSaveRequest) {

        String loginUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        waterIntakeService.saveWaterIntake(loginUserId,waterIntakeSaveRequest);

        return HttpResponse.okBuild(
                SaveResponse.of()
        );
    }

    @Operation(summary = "캘린더 물 정보 수정 api", description = "날짜별 물 섭취 일정을 수정 합니다.")
    @PutMapping("/{waterIntakeId}")
    public HttpResponse<UpdateResponse> waterIntakeModify(@Valid @RequestBody WaterIntakeUpdateRequest waterIntakeUpdateRequest,
                                                         @PathVariable Long waterIntakeId) {

        String loginUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        waterIntakeService.modifyWaterIntake(loginUserId,waterIntakeId,waterIntakeUpdateRequest);

        return HttpResponse.okBuild(
                UpdateResponse.of()
        );

    }

    @Operation(summary = "캘린더 물 성취도 수정 api", description = "하루 물 섭취 성취도를 수정 합니다.")
    @PutMapping("/{waterIntakeId}/achievement")
    public HttpResponse<UpdateResponse> waterIntakeAchieveModify(@Valid @RequestBody WaterIntakeAchieveUpdateRequest waterIntakeAchieveUpdateRequest,
                                                                @PathVariable Long waterIntakeId) {

        String loginUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        waterIntakeService.modifyWaterIntakeAchieve(loginUserId,waterIntakeId,waterIntakeAchieveUpdateRequest);

        return HttpResponse.okBuild(
                UpdateResponse.of()
        );

    }


    @Operation(summary = "캘린더 물 정보 삭제 api", description = "날짜별 물 정보를 삭제합니다.")
    @DeleteMapping("/{waterIntakeId}")
    public HttpResponse<DeleteResponse> waterIntakeRemove(@PathVariable Long waterIntakeId) {

        String loginUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        waterIntakeService.removeWaterIntake(loginUserId,waterIntakeId);

        return HttpResponse.okBuild(
                DeleteResponse.of()
        );

    }
}
