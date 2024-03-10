package com.swig.zigzzang.calender.controller;


import com.swig.zigzzang.calender.dto.request.Supplement.SupplementSaveRequest;
import com.swig.zigzzang.calender.dto.request.Supplement.SupplementUpdateRequest;
import com.swig.zigzzang.calender.dto.response.Supplement.SupplementSaveResponse;
import com.swig.zigzzang.calender.dto.response.Supplement.SupplementUpdateResponse;
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

    @Operation(summary = "영양제 정보 저장 api", description = "하루 영양제 섭취 일정을 저장합니다.")
    @PostMapping("")
    public HttpResponse<SupplementSaveResponse> supplementSave(@Valid @RequestBody SupplementSaveRequest supplementSaveRequest) {

        String loginUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        supplementService.saveSupplement(loginUserId,supplementSaveRequest);

        return HttpResponse.okBuild(
                SupplementSaveResponse.of()
        );

    }

    @Operation(summary = "영양제 정보 수정 api", description = "하루 영양제 섭취 일정을 수정 합니다.")
    @PutMapping("/{supplementId}")
    public HttpResponse<SupplementUpdateResponse> supplementModify(@Valid @RequestBody SupplementUpdateRequest supplementUpdateRequest) {

        String loginUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        supplementService.modifySupplement(loginUserId,supplementUpdateRequest);

        return HttpResponse.okBuild(
                SupplementUpdateResponse.of()
        );

    }





}
