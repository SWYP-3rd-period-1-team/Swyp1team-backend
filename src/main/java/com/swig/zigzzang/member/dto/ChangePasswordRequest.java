package com.swig.zigzzang.member.dto;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordRequest(


        @NotBlank(message = "새로운 비밀번호를 입력해주세요.")
        String newPassword,
        @NotBlank(message = "입력한 비밀번호 확인.")
        String confirmPassword

) {
}
