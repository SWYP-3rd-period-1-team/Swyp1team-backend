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
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Tag(name = "HospitalController", description = "병원 관련 api")
@RestController
@RequestMapping("/api/hospitals")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;


    @Operation(summary = "병원 등록(병원 등록이 필요한 특수한 경우에만 호출)", description = "병원 저장 api")
    @PostMapping("")
    public HttpResponse<HospitalSaveResponse> hospitalSave(@Valid @RequestBody HospitalSaveRequest hospitalSaveRequest) {
        hospitalService.saveHospital(hospitalSaveRequest);

        return HttpResponse.okBuild(
                HospitalSaveResponse.of()
        );
    }

    @Operation(summary = "로그인 회원 병원 북마크 등록 및 해제", description = "hospitalId(구글맵 ID),bookmark 여부를 전달받아 병원 북마크를 등록 및 해제합니다.")
    @PostMapping("{hospitalId}/bookmarks")
    public HttpResponse<HospitalBookmarkSaveResponse> hospitalBookmarkSave(@PathVariable String hospitalId,
                                                                           @Valid @RequestBody HospitalBookmarkSaveRequest hospitalBookmarkSaveRequest) {

        String loginUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        hospitalService.saveHospitalBookmark(loginUserId, hospitalId, hospitalBookmarkSaveRequest);

        return HttpResponse.okBuild(
                HospitalBookmarkSaveResponse.of(hospitalBookmarkSaveRequest.getBookmark())
        );
    }

    @Operation(summary = "병원 상세 보기", description = "병원에 대한 리뷰 및 댓글 목록을 반환합니다.")
    @GetMapping("/{hospitalId}")
    public HttpResponse<HospitalDetailsResponse> hospitalDetails(@PathVariable String hospitalId) { // 리뷰 등록

        List<HospitalCommentDTO> result = hospitalService.findHospitalDetails(hospitalId);

        return HttpResponse.okBuild(
                HospitalDetailsResponse.of(result)
        );
    }

    @Operation(summary = "로그인 회원 리뷰 등록", description = "hospitalId(구글맵 ID) 및 리뷰 관련 파라미터를 전달받아 병원 리뷰를 등록합니다.")
    @PostMapping("{hospitalId}/comments")
    public HttpResponse<CommentInsertResponse> commentAdd(@PathVariable String hospitalId,
                                                          @Valid @RequestBody CommentInsertRequest commentInsertRequest) { // 리뷰 등록

        String loginUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        hospitalService.addComment(loginUserId, hospitalId, commentInsertRequest);

        return HttpResponse.okBuild(
                CommentInsertResponse.of()
        );
    }

    @Operation(summary = "로그인 회원 대댓글 등록", description = "병원 리뷰 또는 댓글에 대한 대댓글을 작성합니다.")
    @PostMapping("{hospitalId}/comments/{commentId}/child-comments")
    public HttpResponse<CommentInsertResponse> childCommentAdd(@PathVariable String hospitalId,
                                                               @PathVariable Long commentId,
                                                               @Valid @RequestBody ChildCommentInsertRequest childCommentInsertRequest) { // 리뷰 등록

        String loginUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        hospitalService.addChildComment(loginUserId, hospitalId, commentId, childCommentInsertRequest);

        return HttpResponse.okBuild(
                CommentInsertResponse.of()
        );
    }

    @Operation(summary = "로그인 회원 댓글 수정", description = "hospitalId(구글맵 ID).commentId(병원 상세보기 api 반환 값 중 hospitalCommentId) " +
            ",리뷰 또는 댓글 수정 내용을 전달받아 로그인 회원 작성 병원 리뷰(댓글)을 수정합니다.")
    @PutMapping("{hospitalId}/comments/{commentId}")
    public HttpResponse<CommentUpdateResponse> commentModify(@PathVariable String hospitalId,
                                                             @PathVariable Long commentId,
                                                             @Valid @RequestBody CommentUpdateRequest commentUpdateRequest) {

        String loginUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        hospitalService.modifyComment(loginUserId, hospitalId, commentId, commentUpdateRequest);

        return HttpResponse.okBuild(
                CommentUpdateResponse.of()
        );
    }

    @Operation(summary = "로그인 회원 댓글 삭제", description = "hospitalId(구글맵 ID).commentId(병원 상세보기 api 반환 값 중 hospitalCommentId) 에 " +
            "를 전달받아 로그인 회원 작성 댓글을 삭제합니다." )
    @DeleteMapping("{hospitalId}/comments/{commentId}")
    public HttpResponse<CommentDeleteResponse> commentRemove(@PathVariable String hospitalId,
                                                             @PathVariable Long commentId) {
        String loginUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        hospitalService.removeComment(loginUserId, hospitalId, commentId);

        return HttpResponse.okBuild(
                CommentDeleteResponse.of()
        );
    }








}
