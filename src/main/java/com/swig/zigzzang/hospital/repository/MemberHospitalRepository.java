package com.swig.zigzzang.hospital.repository;

import com.swig.zigzzang.hospital.domain.MemberHospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberHospitalRepository extends JpaRepository<MemberHospital,Long> {


}
