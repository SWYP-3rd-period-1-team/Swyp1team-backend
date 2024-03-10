package com.swig.zigzzang.calender.repository.QueryDsl;


import com.swig.zigzzang.calender.domain.Supplement;
import com.swig.zigzzang.calender.dto.util.MyCalenderDTO;

import java.util.Optional;


public interface CustomCalenderRepository {

    MyCalenderDTO fetchEntitiesForMemberAndCalender(String calenderDate,Long calenderId); // 날짜별 캘린더 관련 수면 일정,영양제,물 섭취,일정 캘린더 select

}
