package com.swig.zigzzang.hospital.controller;


import com.swig.zigzzang.global.response.HttpResponse;
import com.swig.zigzzang.hospital.dto.request.*;

import com.swig.zigzzang.hospital.dto.response.*;
import com.swig.zigzzang.hospital.dto.util.HospitalCommentDTO;
import com.swig.zigzzang.hospital.service.HospitalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Tag(name = "HospitalController", description = "병원 관련 api")
@RestController
@RequestMapping("/api/hospitals")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;


    @Operation(summary = "병원 등록", description = "병원 상세보기 클릭 시 병원정보를 저장합니다.")
    @PostMapping("")
    public HttpResponse<HospitalSaveResponse> hospitalSave(@Valid @RequestBody HospitalSaveRequest hospitalSaveRequest) {
        hospitalService.saveHospital(hospitalSaveRequest);

        return HttpResponse.okBuild(
                HospitalSaveResponse.of()
        );
    }

    @Operation(summary = "로그인 회원 병원 북마크 등록 및 해제", description = "로그인 userID(쿼리 스트링) 및 구글맵 ID,bookmark(body)를 전달받아 병원 북마크를 등록 및 해제합니다.")
    @PostMapping("/bookmarks")
    public HttpResponse<HospitalBookmarkSaveResponse> hospitalBookmarkSave(@Valid @RequestBody HospitalBookmarkSaveRequest hospitalBookmarkSaveRequest) {
        hospitalService.saveHospitalBookmark(hospitalBookmarkSaveRequest);

        return HttpResponse.okBuild(
                HospitalBookmarkSaveResponse.of(hospitalBookmarkSaveRequest.getBookmark())
        );
    }

    @Operation(summary = "로그인 회원 리뷰 등록", description = "로그인한 회원이 전달받은 googleMapId 에 해당하는 병원에 대한 리뷰를 등록할 수 있게 합니다.")
    @PostMapping("/comments")
    public HttpResponse<CommentInsertResponse> commentAdd(@Valid @RequestBody CommentInsertRequest commentInsertRequest) { // 리뷰 등록
        hospitalService.addComment(commentInsertRequest);

        return HttpResponse.okBuild(
                CommentInsertResponse.of()
        );
    }

    @Operation(summary = "로그인 회원 대댓글 등록", description = "병원 리뷰 또는 댓글에 대한 대댓글을 작성합니다.")
    @PostMapping("/comments/{commentId}/child-comments")
    public HttpResponse<CommentInsertResponse> childCommentAdd(@PathVariable Long commentId,
                                                          @Valid @RequestBody CommentInsertRequest commentInsertRequest) { // 리뷰 등록
        hospitalService.addChildComment(commentId,commentInsertRequest);

        return HttpResponse.okBuild(
                CommentInsertResponse.of()
        );
    }

    @Operation(summary = "병원 상세 보기", description = "병원에 대한 리뷰 및 댓글 목록을 반환합니다.")
    @GetMapping("/hospital-details")
    public HttpResponse<HospitalDetailsResponse> hospitalDetails(@Valid @RequestParam String googleMapId) { // 리뷰 등록

        List<HospitalCommentDTO> result = hospitalService.findHospitalDetails(googleMapId);

        return HttpResponse.okBuild(
                HospitalDetailsResponse.of(result)
        );
    }




    @Operation(summary = "로그인 회원 댓글 수정", description = "commentId(쿼리 스트링)")
    @PutMapping("/comments/{commentId}")
    public HttpResponse<CommentUpdateResponse> commentModify(@PathVariable Long commentId,
                                                             @Valid @RequestBody CommentUpdateRequest commentUpdateRequest) {
        hospitalService.modifyComment(commentId, commentUpdateRequest);

        return HttpResponse.okBuild(
                CommentUpdateResponse.of()
        );
    }


}
