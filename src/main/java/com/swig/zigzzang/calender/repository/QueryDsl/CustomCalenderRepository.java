package com.swig.zigzzang.calender.repository.QueryDsl;

import com.swig.zigzzang.hospital.domain.HospitalComment;
import com.swig.zigzzang.hospital.domain.MemberHospital;

import java.util.List;

public interface CustomCalenderRepository {


    List<HospitalComment> findHospitalCommentsByGoogleMapId(String googleMapId);

    List<MemberHospital> findHospitalsByUserIdWithBookmark(String userId);
}
