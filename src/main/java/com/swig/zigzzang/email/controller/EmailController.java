package com.swig.zigzzang.email.controller;

import com.swig.zigzzang.email.dto.EmailResponseDto;
import com.swig.zigzzang.email.dto.EmailSendSuccessResponse;
import com.swig.zigzzang.global.response.HttpResponse;
import com.swig.zigzzang.member.service.MemberService;
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
    public HttpResponse<EmailSendSuccessResponse> sendMessage(@RequestParam("email") @Valid String email) {
        memberService.sendCodeToEmail(email);

        return HttpResponse.okBuild(
                EmailSendSuccessResponse.of(email)
        );
    }

    @GetMapping("/verifications")
    public ResponseEntity verificationEmail(@RequestParam("email") @Valid  String email,
                                            @RequestParam("code") String authCode) {
        EmailResponseDto response = memberService.verifiedCode(email, authCode);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
