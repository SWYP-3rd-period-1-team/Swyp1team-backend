package com.swig.zigzzang.calender.repository;


import com.swig.zigzzang.calender.domain.Calender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CalenderRepository extends JpaRepository<Calender, Long> {

    @Query("select c from Calender c where c.member.userId=:userId")
    Optional<Calender> findByMember(@Param("userId") String userId);
}
