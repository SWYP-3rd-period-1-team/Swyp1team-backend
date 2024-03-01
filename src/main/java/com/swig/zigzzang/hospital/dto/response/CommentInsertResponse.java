package com.swig.zigzzang.hospital.dto.response;

import lombok.Builder;

@Builder
public record CommentInsertResponse(

        String message
) {
    public static CommentInsertResponse of() {
        return  CommentInsertResponse.builder()
                .message("댓글이 등록되었습니다.")
                .build();

    }
}
