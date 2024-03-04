package com.swig.zigzzang.hospital.repository.QueryDsl;

import com.swig.zigzzang.hospital.domain.Hospital;
import com.swig.zigzzang.hospital.domain.HospitalComment;
import com.swig.zigzzang.hospital.domain.MemberHospital;

import java.util.List;

public interface CustomHospitalCommentRepository {


    List<HospitalComment> findHospitalCommentsByGoogleMapId(String googleMapId);

    List<MemberHospital> findHospitalsByUserIdWithBookmark(String userId);
}
