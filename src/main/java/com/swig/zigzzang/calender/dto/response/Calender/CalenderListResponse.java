package com.swig.zigzzang.calender.dto.response.Calender;

import com.swig.zigzzang.calender.dto.util.MyCalenderDTO;
import lombok.Builder;

@Builder
public record CalenderListResponse(
        MyCalenderDTO myCalender
) {
    public static CalenderListResponse of(MyCalenderDTO myCalenderDTO) {
        return CalenderListResponse.builder()
                .myCalender(myCalenderDTO)
                .build();
    }
}
