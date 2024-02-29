package com.swig.zigzzang.survey.controller;

import com.swig.zigzzang.global.response.HttpResponse;
import com.swig.zigzzang.survey.domain.Survey;
import com.swig.zigzzang.survey.dto.SurveySaveRequest;
import com.swig.zigzzang.survey.dto.SurveySaveResponse;
import com.swig.zigzzang.survey.service.SurveyService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/survey")
@RequiredArgsConstructor
public class SurveyController {
    private final SurveyService surveyService;

    @PostMapping("/save")
    @Operation(summary = "건강설문 결과 저장", description = "건강설문 결과를 사용자별로 저장합니다.")
    public HttpResponse<SurveySaveResponse> save(@RequestBody SurveySaveRequest request) {
        Survey savedsurvey = surveyService.saveSurveyResult(request);

        return HttpResponse.okBuild(
                SurveySaveResponse.of(savedsurvey)
        );

    }
}
