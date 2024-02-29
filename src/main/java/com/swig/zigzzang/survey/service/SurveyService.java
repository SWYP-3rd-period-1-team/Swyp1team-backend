package com.swig.zigzzang.survey.service;

import com.swig.zigzzang.member.domain.Member;
import com.swig.zigzzang.survey.dto.SurveySaveRequest;
import com.swig.zigzzang.member.exception.MemberNotFoundException;
import com.swig.zigzzang.member.repository.MemberRepository;
import com.swig.zigzzang.survey.domain.Survey;
import com.swig.zigzzang.survey.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SurveyService {
    private final MemberRepository memberRepository;
    private final SurveyRepository surveyRepository;

    public Survey saveSurveyResult(SurveySaveRequest surveyrequest) {
        String userid= SecurityContextHolder.getContext().getAuthentication()
                .getName();
        Member member = memberRepository.findByUserId(userid)
                .orElseThrow(MemberNotFoundException::new);

        Survey result = surveyrequest.toEntity(member);
        return surveyRepository.save(result);
    }


}
