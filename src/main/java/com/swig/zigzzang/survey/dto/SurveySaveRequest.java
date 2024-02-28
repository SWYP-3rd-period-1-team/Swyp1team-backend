package com.swig.zigzzang.survey.dto;

import com.swig.zigzzang.member.domain.Member;
import com.swig.zigzzang.survey.domain.Survey;

public record SurveySaveRequest( String targetBodyPart, String diagnosisPart, String presentedSymptom, String department, String disease) {

    public  Survey toEntity(Member member) {
        return Survey.builder()
                .member(member)
                .targetBody(targetBodyPart)
                .diagnosisPart(diagnosisPart)
                .presentedSymptom(presentedSymptom)
                .department(department)
                .disease(disease)
                .build();

    }
}
