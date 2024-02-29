package com.swig.zigzzang.hospital.repository;


import com.swig.zigzzang.hospital.domain.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital,Long> {


    Optional<Hospital> findByGoogleMapId(String googleMapId);


}
