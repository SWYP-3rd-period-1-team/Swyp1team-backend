package com.swig.zigzzang.member.dto;

import jakarta.validation.constraints.NotBlank;

public record ChangeNicknameRequest(
        @NotBlank(message = "새로운 닉네임을 입력해주세요.")
         String newNickname
) {
}
