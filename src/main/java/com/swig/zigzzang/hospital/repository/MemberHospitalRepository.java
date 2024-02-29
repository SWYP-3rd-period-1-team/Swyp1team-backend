package com.swig.zigzzang.hospital.repository;

import com.swig.zigzzang.hospital.domain.MemberHospital;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberHospitalRepository extends JpaRepository<MemberHospital, Long> {

    @Query("select mh from MemberHospital mh where mh.member.userId = :userId and mh.hospital.hospitalId = :hospitalId")
    Optional<MemberHospital> findByUserIdAndHospitalId(@Param("userId") String userId, @Param("hospitalId") Long hospitalId);
}
