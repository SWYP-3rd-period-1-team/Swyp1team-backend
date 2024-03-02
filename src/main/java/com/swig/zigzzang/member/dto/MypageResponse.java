package com.swig.zigzzang.member.dto;

import com.swig.zigzzang.member.domain.Member;
import lombok.Builder;

@Builder
public record MypageResponse(
         Long memberId,
         String nickname,
         String userId,
         String email,
         String profileImage
) {

    public static MypageResponse from(Member member) {
        return MypageResponse.builder()
                .memberId(member.getMemberId())
                .nickname(member.getNickname())
                .userId(member.getUserId())
                .email(member.getEmail())
                .profileImage(member.getProfileimage())
                .build();
    }
}
