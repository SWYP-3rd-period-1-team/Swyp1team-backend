package com.swig.zigzzang.member.repository;

import com.swig.zigzzang.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByUserId(String username);
}
