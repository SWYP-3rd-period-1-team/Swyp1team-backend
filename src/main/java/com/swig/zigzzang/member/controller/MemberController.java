package com.swig.zigzzang.member.controller;

import com.swig.zigzzang.email.dto.EmailResponseDto;
import com.swig.zigzzang.global.response.HttpResponse;
import com.swig.zigzzang.member.domain.Member;
import com.swig.zigzzang.member.dto.MemberJoinRequest;
import com.swig.zigzzang.member.dto.MemberJoinResponse;
import com.swig.zigzzang.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/join")
    @Operation(summary = "회원가입", description = "회원가입을 실행합니다.")

    public HttpResponse<MemberJoinResponse> join(@Valid @RequestBody MemberJoinRequest memberJoinRequest) {
        Member savedmember = memberService.save(memberJoinRequest);
        return HttpResponse.okBuild(
                MemberJoinResponse.of(savedmember)
        );
    }
    @PostMapping("/emails/verification-requests")
    public ResponseEntity sendMessage(@RequestParam("email") @Valid  String email) {
        memberService.sendCodeToEmail(email);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/emails/verifications")
    public ResponseEntity verificationEmail(@RequestParam("email") @Valid  String email,
                                            @RequestParam("code") String authCode) {
        EmailResponseDto response = memberService.verifiedCode(email, authCode);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
