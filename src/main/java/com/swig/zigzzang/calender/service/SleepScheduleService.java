package com.swig.zigzzang.calender.service;


import com.swig.zigzzang.calender.domain.Calender;
import com.swig.zigzzang.calender.domain.SleepSchedule;
import com.swig.zigzzang.calender.dto.request.SleepSchedule.SleepScheduleSaveRequest;
import com.swig.zigzzang.calender.excepiton.CalenderNotExistException;
import com.swig.zigzzang.calender.repository.CalenderRepository;
import com.swig.zigzzang.calender.repository.SleepScheduleRepository;
import com.swig.zigzzang.member.domain.Member;
import com.swig.zigzzang.member.exception.MemberNotExistException;
import com.swig.zigzzang.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SleepScheduleService {

    private final SleepScheduleRepository sleepScheduleRepository;
    private final MemberRepository memberRepository;
    private final CalenderRepository calenderRepository;

    public void saveSleepSchedule(String loginUserId, SleepScheduleSaveRequest sleepScheduleSaveRequest) {

        Member loginMember = memberRepository.findByUserId(loginUserId)
                .orElseThrow(MemberNotExistException::new);

        Calender loginMemberCalender = calenderRepository.findByMember(loginUserId)
                .orElseThrow(CalenderNotExistException::new);

        SleepSchedule saveEntity = sleepScheduleSaveRequest.toEntity(loginMemberCalender);

        sleepScheduleRepository.save(saveEntity);

    }
}
