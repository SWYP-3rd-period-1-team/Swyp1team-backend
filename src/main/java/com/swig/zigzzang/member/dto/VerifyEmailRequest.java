package com.swig.zigzzang.member.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;


public record VerifyEmailRequest(
        @Email(message = "올바른 이메일 주소를 입력하세요.")
         String email
) {
}
