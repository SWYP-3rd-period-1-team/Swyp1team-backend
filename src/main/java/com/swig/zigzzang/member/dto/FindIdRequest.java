package com.swig.zigzzang.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record FindIdRequest(
        @NotBlank(message = "Email은 필수 입력 값입니다.")
        @Email(message = "이메일 형식에 맞지 않습니다.")
         String email
) {
}
