package com.swig.zigzzang.member.controller;

import com.swig.zigzzang.email.dto.EmailResponseDto;
import com.swig.zigzzang.global.response.HttpResponse;
import com.swig.zigzzang.global.security.JWTUtil;
import com.swig.zigzzang.member.domain.Member;
import com.swig.zigzzang.member.dto.FindIdRequest;
import com.swig.zigzzang.member.dto.FindIdResponse;
import com.swig.zigzzang.member.dto.MemberJoinRequest;
import com.swig.zigzzang.member.dto.MemberJoinResponse;
import com.swig.zigzzang.member.dto.MemberLogoutResponse;
import com.swig.zigzzang.member.dto.TokenRefreshRequest;
import com.swig.zigzzang.member.dto.TokenRefreshResponse;
import com.swig.zigzzang.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
    private final JWTUtil jwtUtil;

    @PostMapping("/join")
    @Operation(summary = "회원가입", description = "회원가입을 실행합니다.")

    public HttpResponse<MemberJoinResponse> join(@Valid @RequestBody MemberJoinRequest memberJoinRequest) {
        Member savedmember = memberService.save(memberJoinRequest);
        return HttpResponse.okBuild(
                MemberJoinResponse.of(savedmember)
        );
    }
    @PostMapping("/refresh")
    @Operation(summary = "AccessToken 갱신", description = "만료된 AccessToken을 RefreshToken을 사용해 갱신합니다.")
    public HttpResponse<TokenRefreshResponse> refreshToken(@RequestBody TokenRefreshRequest refreshRequest) {
        String newAccessToken = memberService.refreshToken(refreshRequest.refreshToken());
        return HttpResponse.okBuild(
                TokenRefreshResponse.of(newAccessToken)
        );
    }
    @PatchMapping("/logout")
    @Operation(summary = "로그아웃", description = "로그아웃을 요청하여 RfreshToken을 블랙처리 합니다.")

    public HttpResponse<MemberLogoutResponse> logout(HttpServletRequest request) {

        String username= SecurityContextHolder.getContext().getAuthentication()
                .getName();
        String encryptedRefreshToken = jwtUtil.getRefreshToken(request);
        String blacklist = memberService.logout(encryptedRefreshToken);

        return HttpResponse.okBuild(
                MemberLogoutResponse.from(username,blacklist)
        );
    }

    @PostMapping("/find-id")
    @Operation(summary = "아이디 찾기", description = "이메일을 통해 사용자 아이디를 찾습니다.")
    public HttpResponse<FindIdResponse> findId(@Valid @RequestBody FindIdRequest findIdRequest) {
        String foundId = memberService.findIdByEmail(findIdRequest.email());
        return HttpResponse.okBuild(
                FindIdResponse.of(foundId)
        );
    }


}
