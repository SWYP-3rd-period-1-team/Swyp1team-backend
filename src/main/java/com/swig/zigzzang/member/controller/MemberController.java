package com.swig.zigzzang.member.controller;

import com.swig.zigzzang.global.response.HttpResponse;
import com.swig.zigzzang.global.security.JWTUtil;
import com.swig.zigzzang.member.domain.Member;
import com.swig.zigzzang.member.dto.ChangeNicknameRequest;
import com.swig.zigzzang.member.dto.ChangeNicknameResponse;
import com.swig.zigzzang.member.dto.ChangePasswordRequest;
import com.swig.zigzzang.member.dto.ChangePasswordResponse;
import com.swig.zigzzang.member.dto.FindIdRequest;
import com.swig.zigzzang.member.dto.FindIdResponse;
import com.swig.zigzzang.member.dto.FindPasswordRequest;
import com.swig.zigzzang.member.dto.FindPasswordResponse;
import com.swig.zigzzang.member.dto.MemberJoinRequest;
import com.swig.zigzzang.member.dto.MemberJoinResponse;
import com.swig.zigzzang.member.dto.MemberLogoutResponse;
import com.swig.zigzzang.member.dto.MypageResponse;
import com.swig.zigzzang.member.dto.TokenRefreshRequest;
import com.swig.zigzzang.member.dto.TokenRefreshResponse;
import com.swig.zigzzang.member.dto.VerifyEmailRequest;
import com.swig.zigzzang.member.service.MemberService;
import com.swig.zigzzang.profile.dto.ChangeProfileImageRequest;
import com.swig.zigzzang.profile.dto.ChangeProfileImageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/members")
@Tag(name = "MemberController", description = "")

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
        String newAccessToken = memberService.reisuueAccessToken(refreshRequest.refreshToken());
        String newRefreshToken = memberService.reissueRefreshToken(refreshRequest.refreshToken());
        return HttpResponse.okBuild(
                TokenRefreshResponse.of(newAccessToken,newRefreshToken)
        );
    }
    @PatchMapping("/logout")
    @Operation(summary = "로그아웃", description = "로그아웃을 요청하여 RfreshToken을 블랙처리 합니다.")

    public HttpResponse<MemberLogoutResponse> logout(HttpServletRequest request) {

        String username= memberService.getUsernameBySecurityContext();
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

    @PostMapping("/find-password")
    @Operation(summary = "비밀번호 찾기", description = "아이디와 이메일로 임시 비밀번호를 발급합니다.")
    public HttpResponse<FindPasswordResponse> findPassword(@Valid @RequestBody FindPasswordRequest findPasswordRequest) {
        String email = memberService.findPassword(findPasswordRequest.userId(), findPasswordRequest.email());
        return HttpResponse.okBuild(
                FindPasswordResponse.of(email)
        );
    }
    @PatchMapping("/change-password")
    @Operation(summary = "비밀번호 변경", description = "현재 비밀번호를 확인하고 새로운 비밀번호로 변경합니다.")
    public HttpResponse<ChangePasswordResponse> changePassword(@RequestBody @Valid ChangePasswordRequest changePasswordRequest) {

        String changedPassword = memberService.changePassword(changePasswordRequest);
        return HttpResponse.okBuild(
                ChangePasswordResponse.of(changedPassword)
        );
    }

    @PatchMapping("/change-nickname")
    @Operation(summary = "닉네임 변경", description = "새로운 닉네임으로 변경합니다.")
    public HttpResponse<ChangeNicknameResponse> changeNickname(@RequestBody @Valid ChangeNicknameRequest changeNicknameRequest) {

        String changedNickname = memberService.changeNickname(changeNicknameRequest);
        return HttpResponse.okBuild(
                ChangeNicknameResponse.of(changedNickname)

        );
    }


    @GetMapping("/my-page")
    @Operation(summary = "마이페이지 정보 조회", description = "현재 로그인한 사용자의 마이페이지 정보를 조회합니다.")
    public HttpResponse<MypageResponse> getMyPage() {
        String username = memberService.getUsernameBySecurityContext();
        Member member = memberService.findMemberByUsername(username);
        return HttpResponse.okBuild(
                MypageResponse.from(member)
        );
    }
    @PostMapping("/verify-email")
    @Operation(summary = "사용자 이메일 확인", description = "비밀번호 변경시 DB에 저장된 이메일과 입력된 이메일을 비교하여 확인합니다.")
    public HttpResponse<String> verifyEmail(@RequestBody VerifyEmailRequest verifyEmailRequest) {
        memberService.verifyEmail(verifyEmailRequest.email());
        return HttpResponse.okBuild("이메일이 확인되었습니다.");
    }

}
