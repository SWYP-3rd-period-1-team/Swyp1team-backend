package com.swig.zigzzang.hospital.repository.QueryDsl;

import com.swig.zigzzang.hospital.domain.Hospital;
import com.swig.zigzzang.hospital.domain.HospitalComment;

import java.util.List;

public interface CustomHospitalCommentRepository {


    List<HospitalComment> findHospitalCommentsByGoogleMapId(String googleMapId);

    List<Hospital> findHospitalsByUserIdWithBookmark(String userId);
}
