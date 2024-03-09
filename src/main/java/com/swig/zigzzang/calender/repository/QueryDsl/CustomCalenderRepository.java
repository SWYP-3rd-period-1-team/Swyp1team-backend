package com.swig.zigzzang.calender.repository.QueryDsl;


import com.querydsl.core.Tuple;
import com.swig.zigzzang.calender.dto.util.MyCalenderDTO;

import java.util.List;

public interface CustomCalenderRepository {

    MyCalenderDTO fetchEntitiesForMemberAndCalender(Long calenderId);
}
