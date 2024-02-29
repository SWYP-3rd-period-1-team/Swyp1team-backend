package com.swig.zigzzang.hospital.dto.response;

import lombok.Builder;

@Builder
public record CommentInsertResponse(

        String message
) {
    public static CommentInsertResponse of() {
        return new CommentInsertResponse("댓글이 등록되었습니다.");

    }
}
