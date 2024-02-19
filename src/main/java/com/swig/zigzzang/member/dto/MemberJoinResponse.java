package com.swig.zigzzang.member.dto;

import com.swig.zigzzang.member.domain.Member;
import lombok.Builder;

@Builder
public record MemberJoinResponse(
        Long id, String userid,String password,String email,String nickname


) {


    public static MemberJoinResponse of(Member member) {
        return MemberJoinResponse.builder()
                .id(member.getMemberId())
                .userid(member.getUserId())
                .email(member.getEmail())
                .password(member.getPassword())
                .nickname(member.getNickname())
                .build();

    }
}
