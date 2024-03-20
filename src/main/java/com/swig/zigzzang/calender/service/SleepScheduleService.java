package com.swig.zigzzang.calender.service;


import com.swig.zigzzang.calender.domain.Calender;
import com.swig.zigzzang.calender.domain.SleepSchedule;
import com.swig.zigzzang.calender.dto.request.SleepSchedule.SleepScheduleAchieveUpdateRequest;
import com.swig.zigzzang.calender.dto.request.SleepSchedule.SleepScheduleSaveRequest;
import com.swig.zigzzang.calender.dto.request.SleepSchedule.SleepScheduleUpdateRequest;
import com.swig.zigzzang.calender.dto.request.Supplement.SupplementAchieveUpdateRequest;
import com.swig.zigzzang.calender.excepiton.CalenderNotExistException;
import com.swig.zigzzang.calender.excepiton.DeleteException;
import com.swig.zigzzang.calender.excepiton.UpdateException;
import com.swig.zigzzang.calender.repository.Calender.CalenderRepository;
import com.swig.zigzzang.calender.repository.SleepSchedule.CustomSleepScheduleRepository;
import com.swig.zigzzang.calender.repository.SleepSchedule.SleepScheduleRepository;
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
    private final CustomSleepScheduleRepository customSleepScheduleRepository;

    public void saveSleepSchedule(String loginUserId, SleepScheduleSaveRequest sleepScheduleSaveRequest) {

        Member loginMember = memberRepository.findByUserId(loginUserId)
                .orElseThrow(MemberNotExistException::new);

        Calender loginMemberCalender = calenderRepository.findByMember(loginUserId)
                .orElseThrow(CalenderNotExistException::new);

        SleepSchedule saveEntity = sleepScheduleSaveRequest.toEntity(loginMemberCalender);

        sleepScheduleRepository.save(saveEntity);

    }

    public void modifySleepSchedule(String loginUserId, Long sleepScheduleId, SleepScheduleUpdateRequest sleepScheduleUpdateRequest) {

        SleepSchedule target = customSleepScheduleRepository.findByMemberAndCalender(loginUserId, sleepScheduleId)
                .orElseThrow(UpdateException::new);

        target.updateEntity(sleepScheduleUpdateRequest);

        sleepScheduleRepository.save(target);
    }

    public void removeSleepSchedule(String loginUserId, Long sleepScheduleId) {

        SleepSchedule target = customSleepScheduleRepository.findByMemberAndCalender(loginUserId, sleepScheduleId)
                .orElseThrow(DeleteException::new);

        sleepScheduleRepository.delete(target);

    }

    public void modifySleepScheduleAchieve(String loginUserId, Long sleepScheduleId, SleepScheduleAchieveUpdateRequest sleepScheduleAchieveUpdateRequest) {

        SleepSchedule target = customSleepScheduleRepository.findByMemberAndCalender(loginUserId, sleepScheduleId)
                .orElseThrow(UpdateException::new);

        target.updateAchieveEntity(sleepScheduleAchieveUpdateRequest);

        sleepScheduleRepository.save(target);
    }
}
