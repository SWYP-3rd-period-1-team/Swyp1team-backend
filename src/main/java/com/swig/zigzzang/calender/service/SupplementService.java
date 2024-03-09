package com.swig.zigzzang.calender.service;


import com.swig.zigzzang.calender.domain.Calender;
import com.swig.zigzzang.calender.domain.Supplement;
import com.swig.zigzzang.calender.dto.request.SupplementSaveRequest;
import com.swig.zigzzang.calender.excepiton.CalenderNotExistException;
import com.swig.zigzzang.calender.repository.CalenderRepository;
import com.swig.zigzzang.calender.repository.SupplementRepository;
import com.swig.zigzzang.member.domain.Member;
import com.swig.zigzzang.member.exception.MemberNotExistException;
import com.swig.zigzzang.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplementService {

    private final SupplementRepository supplementRepository;
    private final MemberRepository memberRepository;
    private final CalenderRepository calenderRepository;


    public void saveSupplement(String loginUserId, SupplementSaveRequest supplementSaveRequest) { // 영양제 섭취 일정 저장

        Member loginMember = memberRepository.findByUserId(loginUserId)
                .orElseThrow(MemberNotExistException::new);

        Calender loginMemberCalender = calenderRepository.findByMember(loginUserId)
                .orElseThrow(CalenderNotExistException::new);

        Supplement saveEntity = supplementSaveRequest.toEntity(loginMember, loginMemberCalender);

        supplementRepository.save(saveEntity);

    }
}
