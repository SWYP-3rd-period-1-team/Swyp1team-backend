package com.swig.zigzzang.member.repository;

import com.swig.zigzzang.member.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserId(String username);
    Optional<Member> findByEmail(String email);

    Optional<Member> findByNickname(String nickname);

    Optional<Member> findByUserIdAndEmail(String userId, String email);

}
