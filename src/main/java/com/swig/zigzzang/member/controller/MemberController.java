package com.swig.zigzzang.member.controller;

import com.swig.zigzzang.member.domain.Member;
import com.swig.zigzzang.member.dto.MemberJoinRequest;
import com.swig.zigzzang.member.dto.MemberJoinResponse;
import com.swig.zigzzang.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<MemberJoinResponse> join(@Valid @RequestBody MemberJoinRequest memberJoinRequest) {
        Member savedmember = memberService.save(memberJoinRequest);
        return ResponseEntity.ok(
                MemberJoinResponse.of(savedmember)
        );
    }

}
