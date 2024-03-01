package com.swig.zigzzang.hospital.dto.response;

import com.swig.zigzzang.hospital.dto.request.CommentUpdateRequest;
import lombok.Builder;

@Builder
public record CommentUpdateResponse(
        String message
) {
    public static CommentUpdateResponse of() {
        return CommentUpdateResponse.builder()
                .message("댓글이 수정되었습니다.")
                .build();

    }
}
