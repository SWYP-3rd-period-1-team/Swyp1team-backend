package com.swig.zigzzang.calender.service;


import com.swig.zigzzang.calender.domain.Calender;
import com.swig.zigzzang.calender.domain.WaterIntake;
import com.swig.zigzzang.calender.dto.request.WaterIntakeSaveRequest;
import com.swig.zigzzang.calender.excepiton.CalenderNotExistException;
import com.swig.zigzzang.calender.repository.CalenderRepository;
import com.swig.zigzzang.calender.repository.WaterIntakeRepository;
import com.swig.zigzzang.member.domain.Member;
import com.swig.zigzzang.member.exception.MemberNotExistException;
import com.swig.zigzzang.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class WaterIntakeService {

    private final WaterIntakeRepository waterIntakeRepository;
    private final MemberRepository memberRepository;
    private final CalenderRepository calenderRepository;

    public void saveWaterIntake(String loginUserId, WaterIntakeSaveRequest waterIntakeSaveRequest) { // 물 섭취 저장 service

        Member loginMember = memberRepository.findByUserId(loginUserId)
                .orElseThrow(MemberNotExistException::new);

        Calender loginMemberCalender = calenderRepository.findByMember(loginUserId)
                .orElseThrow(CalenderNotExistException::new);

        WaterIntake saveEntity = waterIntakeSaveRequest.toEntity(loginMember, loginMemberCalender);

        waterIntakeRepository.save(saveEntity);

    }
}
