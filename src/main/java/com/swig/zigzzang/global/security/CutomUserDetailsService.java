package com.swig.zigzzang.global.security;

import com.swig.zigzzang.member.domain.Member;
import com.swig.zigzzang.member.repository.MemberRepository;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CutomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;


    public CutomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> member = memberRepository.findByUserId(username);


        if (member != null) {

            return new CustomUserDetails(member.get());
        }

        return null;
    }
}
