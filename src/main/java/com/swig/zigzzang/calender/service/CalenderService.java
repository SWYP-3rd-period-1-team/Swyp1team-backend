package com.swig.zigzzang.calender.service;


import com.swig.zigzzang.calender.domain.Calender;
import com.swig.zigzzang.calender.dto.util.MyCalenderDTO;
import com.swig.zigzzang.calender.repository.Calender.CalenderRepository;
import com.swig.zigzzang.calender.repository.Calender.CustomCalenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CalenderService {

    private final CustomCalenderRepository customCalenderRepository;
    private final CalenderRepository calenderRepository;

    public MyCalenderDTO myListCalender(String calenderDate,String loginUserId) { // 내 캘린더 목록

        Calender target = calenderRepository.findByMember(loginUserId)
                .orElseThrow();

        return customCalenderRepository.fetchEntitiesForMemberAndCalender(calenderDate,target.getCalenderId());

    }
}
