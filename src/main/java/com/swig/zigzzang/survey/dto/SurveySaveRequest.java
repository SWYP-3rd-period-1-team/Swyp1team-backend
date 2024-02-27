package com.swig.zigzzang.survey.dto;

import com.swig.zigzzang.member.domain.Member;
import com.swig.zigzzang.survey.domain.Survey;

public record SurveySaveRequest(String userId, String targetBodyPart, String diagnosisPart, String presentedSymptom) {

    public  Survey toEntity(Member member) {
       return Survey.builder()
                .member(member)
                .targetBody(targetBodyPart)
                .diagnosisPart(diagnosisPart)
                .presentedSymptom(presentedSymptom)
                .build();

    }
}
