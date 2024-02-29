package com.swig.zigzzang.member.dto;

import com.swig.zigzzang.member.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.userdetails.User;


public record MemberJoinRequest (
        @NotNull(message = "사용자 아이디는 필수 입력값입니다.")
        @Schema(description = "사용자 아이디", nullable = false, example = "test")
        String userId,
        @NotNull(message = "사용자 비밀번호는 필수 입력값입니다.")
        @Schema(description = "사용자 비밀번호", nullable = false, example = "test")
        String password,
        @NotNull(message = "사용자 이메일은 필수 입력값입니다.")
        @Schema(description = "사용자 이메일", nullable = false, example = "test@google.com")
        String email,
        @NotNull(message = "사용자 닉네임은 필수 입력값입니다.")
        @Schema(description = "사용자 아이디", nullable = false, example = "test")
        String nickname

){

    public Member toEntity() {
        return Member.builder()
                .userId(userId)
                .password(password)
                .nickname(nickname)
                .email(email)
                .build();

    }


}

