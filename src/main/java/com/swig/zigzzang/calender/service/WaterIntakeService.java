package com.swig.zigzzang.calender.service;


import com.swig.zigzzang.calender.domain.Calender;
import com.swig.zigzzang.calender.domain.WaterIntake;
import com.swig.zigzzang.calender.dto.request.WaterIntake.WaterIntakeAchieveUpdateRequest;
import com.swig.zigzzang.calender.dto.request.WaterIntake.WaterIntakeSaveRequest;
import com.swig.zigzzang.calender.dto.request.WaterIntake.WaterIntakeUpdateRequest;
import com.swig.zigzzang.calender.excepiton.CalenderNotExistException;
import com.swig.zigzzang.calender.excepiton.DeleteException;
import com.swig.zigzzang.calender.excepiton.UpdateException;
import com.swig.zigzzang.calender.repository.Calender.CalenderRepository;
import com.swig.zigzzang.calender.repository.WaterIntake.CustomWaterIntakeRepository;
import com.swig.zigzzang.calender.repository.WaterIntake.WaterIntakeRepository;
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
    private final CustomWaterIntakeRepository customWaterIntakeRepository;

    public void saveWaterIntake(String loginUserId, WaterIntakeSaveRequest waterIntakeSaveRequest) { // 물 섭취 저장

        Member loginMember = memberRepository.findByUserId(loginUserId)
                .orElseThrow(MemberNotExistException::new);

        Calender loginMemberCalender = calenderRepository.findByMember(loginUserId)
                .orElseThrow(CalenderNotExistException::new);

        WaterIntake saveEntity = waterIntakeSaveRequest.toEntity(loginMember, loginMemberCalender);

        waterIntakeRepository.save(saveEntity);

    }

    public void modifyWaterIntake(String loginUserId, Long waterIntakeId, WaterIntakeUpdateRequest waterIntakeUpdateRequest) { // 물 섭취 정보 수정

        WaterIntake target = customWaterIntakeRepository.findByMemberAndCalender(loginUserId, waterIntakeId)
                .orElseThrow(UpdateException::new);

        target.updateEntity(waterIntakeUpdateRequest);

        waterIntakeRepository.save(target);
    }

    public void removeWaterIntake(String loginUserId, Long waterIntakeId) {

        WaterIntake target = customWaterIntakeRepository.findByMemberAndCalender(loginUserId, waterIntakeId)
                .orElseThrow(DeleteException::new);

        waterIntakeRepository.delete(target);

    }

    public void modifyWaterIntakeAchieve(String loginUserId, Long waterIntakeId, WaterIntakeAchieveUpdateRequest waterIntakeAchieveUpdateRequest) {

        WaterIntake target = customWaterIntakeRepository.findByMemberAndCalender(loginUserId, waterIntakeId)
                .orElseThrow(UpdateException::new);

        target.updateAchieveEntity(waterIntakeAchieveUpdateRequest);

        waterIntakeRepository.save(target);
    }
}
