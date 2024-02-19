package com.swig.zigzzang.member.service;

import com.swig.zigzzang.member.domain.Member;
import com.swig.zigzzang.member.dto.MemberJoinRequest;
import com.swig.zigzzang.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public Member save(MemberJoinRequest memberJoinRequest) {
        Member member = memberJoinRequest.toEntity();

        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));

        Member savedmember = memberRepository.save(member);


        return savedmember;
    }

}
