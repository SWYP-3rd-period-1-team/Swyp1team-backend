package com.swig.zigzzang.hospital.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
public record ChildCommentInsertResponse(
        String message
) {
    public static ChildCommentInsertResponse of() {
        return ChildCommentInsertResponse.builder()
                .message("댓글이 등록되었습니다.")
                .build();
    }
}
