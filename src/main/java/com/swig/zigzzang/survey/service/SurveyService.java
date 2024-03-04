package com.swig.zigzzang.survey.service;

import com.swig.zigzzang.member.domain.Member;
import com.swig.zigzzang.member.service.MemberService;
import com.swig.zigzzang.survey.dto.SurveyResponse;
import com.swig.zigzzang.survey.dto.SurveySaveRequest;
import com.swig.zigzzang.member.exception.MemberNotFoundException;
import com.swig.zigzzang.member.repository.MemberRepository;
import com.swig.zigzzang.survey.domain.Survey;
import com.swig.zigzzang.survey.exception.SurveyMemberNotEqualException;
import com.swig.zigzzang.survey.exception.SurveyNotFoundException;
import com.swig.zigzzang.survey.repository.SurveyRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SurveyService {
    private final MemberRepository memberRepository;
    private final SurveyRepository surveyRepository;
    private final MemberService memberService;

    public Survey saveSurveyResult(SurveySaveRequest surveyrequest) {
        String userid = memberService.getUsernameBySecurityContext();
        Member member = memberRepository.findByUserId(userid)
                .orElseThrow(MemberNotFoundException::new);

        Survey result = surveyrequest.toEntity(member);
        return surveyRepository.save(result);
    }

    public List<SurveyResponse> getSurveysByUserId() {
        String userId = memberService.getUsernameBySecurityContext();
        memberRepository.findByUserId(userId)
                .orElseThrow(MemberNotFoundException::new);

        List<Survey> surveys = surveyRepository.findAllJoinFetch(userId);

        return surveys.stream()
                .map(survey -> SurveyResponse.of(survey))
                .collect(Collectors.toList());
    }

    public void deleteSurvey(Long surveyId) {
        String userid = memberService.getUsernameBySecurityContext();
        Member member = memberRepository.findByUserId(userid)
                .orElseThrow(MemberNotFoundException::new);

        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(SurveyNotFoundException::new);
        if (!survey.getMember().getUserId().equals(member.getUserId())) {
            throw new SurveyMemberNotEqualException();
        }

        surveyRepository.deleteById(surveyId);
    }


}
