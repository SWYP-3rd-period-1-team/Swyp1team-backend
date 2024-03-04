package com.swig.zigzzang.survey.dto;

import com.swig.zigzzang.survey.domain.Survey;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record SurveyResponse(Long surveyId,
                             String nickname,
                             String targetBody,
                             String diagnosisPart,
                             String presentedSymptom,
                             String department,
                             String disease,
                             LocalDateTime dateTime

) {
    public static SurveyResponse of(Survey survey) {
        return SurveyResponse.builder()
                .surveyId(survey.getSurveyId())
                .targetBody(survey.getTargetBody())
                .diagnosisPart(survey.getDiagnosisPart())
                .presentedSymptom(survey.getDiagnosisPart())
                .department(survey.getDepartment())
                .disease(survey.getDisease())
                .nickname(survey.getMember().getNickname())
                .dateTime(survey.getCreatedDate())
                .build();
    }

}
