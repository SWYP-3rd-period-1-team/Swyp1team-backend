package com.swig.zigzzang.member.dto;

import com.swig.zigzzang.member.domain.Member;
import lombok.Builder;

@Builder
public record MemberJoinResponse(
        String surveyUrl,String message


) {

    public static MemberJoinResponse of(Member member) {
        return MemberJoinResponse.builder()
                .message(member.getNickname()+"님 회원가입을 축하합니다 !")
                .surveyUrl("https://www.zigzzang.site/Login")
                .build();

    }
}
