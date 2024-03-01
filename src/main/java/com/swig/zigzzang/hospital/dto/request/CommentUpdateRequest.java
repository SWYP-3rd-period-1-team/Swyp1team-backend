package com.swig.zigzzang.hospital.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentUpdateRequest {

    @NotNull(message = "댓글내용을 입력해 주세요")
    @Schema(description = "댓글 내용", nullable = false, example = "댓글 내용")
    private String content;

}
