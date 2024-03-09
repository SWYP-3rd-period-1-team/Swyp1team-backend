package com.swig.zigzzang.calender.service;


import com.swig.zigzzang.calender.domain.Calender;
import com.swig.zigzzang.calender.domain.Schedule;
import com.swig.zigzzang.calender.dto.request.ScheduleSaveRequest;
import com.swig.zigzzang.calender.excepiton.CalenderNotExistException;
import com.swig.zigzzang.calender.repository.CalenderRepository;
import com.swig.zigzzang.calender.repository.ScheduleRepository;
import com.swig.zigzzang.member.domain.Member;
import com.swig.zigzzang.member.exception.MemberNotExistException;
import com.swig.zigzzang.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;
    private final CalenderRepository calenderRepository;


    public void saveSchedule(String loginUserId, ScheduleSaveRequest scheduleSaveRequest) {

        Member loginMember = memberRepository.findByUserId(loginUserId)
                .orElseThrow(MemberNotExistException::new);

        Calender loginMemberCalender = calenderRepository.findByMember(loginUserId)
                .orElseThrow(CalenderNotExistException::new);

        Schedule saveEntity = scheduleSaveRequest.toEntity(loginMemberCalender);

        scheduleRepository.save(saveEntity);
    }
}
