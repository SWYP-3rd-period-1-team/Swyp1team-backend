package com.swig.zigzzang.hospital.dto.response;

import lombok.Builder;

@Builder
public record CommentDeleteResponse(
        String message
) {
    public static CommentDeleteResponse of() {
        return CommentDeleteResponse.builder()
                .message("댓글이 삭제되었습니다.")
                .build();

    }
}
