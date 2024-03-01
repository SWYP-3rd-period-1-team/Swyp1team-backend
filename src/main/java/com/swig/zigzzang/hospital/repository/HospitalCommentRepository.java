package com.swig.zigzzang.hospital.repository;

import com.swig.zigzzang.hospital.domain.HospitalComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HospitalCommentRepository extends JpaRepository<HospitalComment,Long> {

    Optional<HospitalComment> findByHospitalCommentId(Long commentId);


}
