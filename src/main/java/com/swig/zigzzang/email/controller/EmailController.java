package com.swig.zigzzang.email.controller;

import com.swig.zigzzang.email.dto.EmailResponseDto;
import com.swig.zigzzang.email.dto.EmailSendSuccessResponse;
import com.swig.zigzzang.email.dto.EmailVerifySuccessResponse;
import com.swig.zigzzang.global.response.HttpResponse;
import com.swig.zigzzang.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/emails")
@RequiredArgsConstructor
public class EmailController {
    private final MemberService memberService;


    @PostMapping("/verification-requests")
    @Operation(summary = "이메일 인증번호 전송", description = "주어진 쿼리스트링의 이메일로 인증코드를 전송합니다.")
    public HttpResponse<EmailSendSuccessResponse> sendMessage(@RequestParam("email") @Valid String email) {
        memberService.sendCodeToEmail(email);

        return HttpResponse.okBuild(
                EmailSendSuccessResponse.of(email)
        );
    }

    @GetMapping("/verifications")
    @Operation(summary = "이메일 검증", description = "이메일 인증코드와 대조하여 검증결과를 전송합니다.")
    public HttpResponse<EmailVerifySuccessResponse> verificationEmail(@RequestParam("email") @Valid  String email,
                                                                      @RequestParam("code") String authCode) {
        Boolean result = memberService.verifiedCode(email, authCode);

        return HttpResponse.okBuild(
                EmailVerifySuccessResponse.of(result)
        );
    }
}
