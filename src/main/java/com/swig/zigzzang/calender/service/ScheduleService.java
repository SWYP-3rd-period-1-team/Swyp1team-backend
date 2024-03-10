package com.swig.zigzzang.calender.service;


import com.swig.zigzzang.calender.domain.Calender;
import com.swig.zigzzang.calender.domain.Schedule;
import com.swig.zigzzang.calender.dto.request.Schedule.ScheduleSaveRequest;
import com.swig.zigzzang.calender.dto.request.Schedule.ScheduleUpdateRequest;
import com.swig.zigzzang.calender.excepiton.CalenderNotExistException;
import com.swig.zigzzang.calender.excepiton.DeleteException;
import com.swig.zigzzang.calender.excepiton.UpdateException;
import com.swig.zigzzang.calender.repository.Calender.CalenderRepository;
import com.swig.zigzzang.calender.repository.Schedule.CustomScheduleRepository;
import com.swig.zigzzang.calender.repository.Schedule.ScheduleRepository;
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
    private final CustomScheduleRepository customScheduleRepository;


    public void saveSchedule(String loginUserId, ScheduleSaveRequest scheduleSaveRequest) {

        Member loginMember = memberRepository.findByUserId(loginUserId)
                .orElseThrow(MemberNotExistException::new);

        Calender loginMemberCalender = calenderRepository.findByMember(loginUserId)
                .orElseThrow(CalenderNotExistException::new);

        Schedule saveEntity = scheduleSaveRequest.toEntity(loginMemberCalender);

        scheduleRepository.save(saveEntity);
    }

    public void modifySchedule(String loginUserId, Long scheduleId, ScheduleUpdateRequest scheduleUpdateRequest) {

        Schedule target = customScheduleRepository.findByMemberAndCalender(loginUserId, scheduleId)
                .orElseThrow(UpdateException::new);

        target.updateEntity(scheduleUpdateRequest);

        scheduleRepository.save(target);
    }

    public void removeSchedule(String loginUserId, Long scheduleId) {

        Schedule target = customScheduleRepository.findByMemberAndCalender(loginUserId, scheduleId)
                .orElseThrow(DeleteException::new);

        scheduleRepository.delete(target);
    }
}
