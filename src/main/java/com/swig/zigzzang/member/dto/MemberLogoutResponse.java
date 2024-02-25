package com.swig.zigzzang.member.dto;

import lombok.Builder;

@Builder
public record MemberLogoutResponse(String message,String blacklist) {
    public static MemberLogoutResponse from(String username,String blacklist){

        return MemberLogoutResponse.builder()
                .message("요청하신 " + username + " 의 리프레시토큰이 블랙리스트 처리되었습니다."
                        + " 새롭게 로그인후 RT,AT를 발급받아주세요")
                .blacklist(blacklist)
                .build();
    }
}
