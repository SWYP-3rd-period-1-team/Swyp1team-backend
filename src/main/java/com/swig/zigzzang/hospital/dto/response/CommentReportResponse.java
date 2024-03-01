package com.swig.zigzzang.hospital.dto.response;

import lombok.Builder;

@Builder
public record CommentReportResponse(
        String message
) {
    public static CommentReportResponse of() {
        return CommentReportResponse.builder()
                .message("댓글 신고가 완료되었습니다.")
                .build();
    }
}
