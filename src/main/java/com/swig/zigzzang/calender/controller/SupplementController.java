package com.swig.zigzzang.calender.controller;


import com.swig.zigzzang.calender.dto.request.Supplement.SupplementAchieveUpdateRequest;
import com.swig.zigzzang.calender.dto.request.Supplement.SupplementSaveRequest;
import com.swig.zigzzang.calender.dto.request.Supplement.SupplementUpdateRequest;
import com.swig.zigzzang.calender.dto.response.DeleteResponse;
import com.swig.zigzzang.calender.dto.response.SaveResponse;
import com.swig.zigzzang.calender.dto.response.UpdateResponse;
import com.swig.zigzzang.calender.service.SupplementService;
import com.swig.zigzzang.global.response.HttpResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Tag(name = "SupplementController", description = "캘린더 영양제 관련 api")
@RestController
@RequestMapping("/api/calenders/supplements")
@RequiredArgsConstructor
public class SupplementController {

    private final SupplementService supplementService;

    @Operation(summary = "날짜별 영양제 정보 저장 api", description = "날짜별 하루 영양제 섭취 일정을 저장합니다.")
    @PostMapping("")
    public HttpResponse<SaveResponse> supplementSave(@Valid @RequestBody SupplementSaveRequest supplementSaveRequest) {

        String loginUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        supplementService.saveSupplement(loginUserId,supplementSaveRequest);

        return HttpResponse.okBuild(
                SaveResponse.of()
        );

    }

    @Operation(summary = "캘린더 영양제 정보 수정 api", description = "하루 영양제 섭취 일정을 수정 합니다.")
    @PutMapping("/{supplementId}")
    public HttpResponse<UpdateResponse> supplementModify(@Valid @RequestBody SupplementUpdateRequest supplementUpdateRequest,
                                                        @PathVariable Long supplementId) {

        String loginUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        supplementService.modifySupplement(loginUserId,supplementId,supplementUpdateRequest);

        return HttpResponse.okBuild(
                UpdateResponse.of()
        );

    }

    @Operation(summary = "캘린더 영양제 성취도 수정 api", description = "하루 영양제 섭취 일정을 수정 합니다.")
    @PutMapping("/{supplementId}/achievement")
    public HttpResponse<UpdateResponse> supplementAchieveModify(@Valid @RequestBody SupplementAchieveUpdateRequest supplementAchieveUpdateRequest,
                                                         @PathVariable Long supplementId) {

        String loginUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        supplementService.modifySupplementAchieve(loginUserId,supplementId,supplementAchieveUpdateRequest);

        return HttpResponse.okBuild(
                UpdateResponse.of()
        );

    }

    @Operation(summary = "캘린더 영양제 정보 삭제 api", description = "날짜별 캘린더 영양제 정보를 삭제합니다.")
    @DeleteMapping("/{supplementId}")
    public HttpResponse<DeleteResponse> supplementRemove(@PathVariable Long supplementId) {

        String loginUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        supplementService.removeSupplement(loginUserId,supplementId);

        return HttpResponse.okBuild(
                DeleteResponse.of()
        );

    }

}
