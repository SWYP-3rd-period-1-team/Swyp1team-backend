package com.swig.zigzzang.survey.controller;

import com.swig.zigzzang.global.response.HttpResponse;
import com.swig.zigzzang.survey.domain.Survey;
import com.swig.zigzzang.survey.dto.SurveyResponse;
import com.swig.zigzzang.survey.dto.SurveySaveRequest;
import com.swig.zigzzang.survey.dto.SurveySaveResponse;
import com.swig.zigzzang.survey.service.SurveyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/survey")
@Tag(name = "SurveyController", description = "건강설문 api")

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
    @GetMapping("/list")
    @Operation(summary = "나의 질병 리스트 조회", description = "로그인한사용자의 건강설문 리스트를 조회합니다.")
    public HttpResponse<List<SurveyResponse>> getSurveyList() {
        List<SurveyResponse> surveyList = surveyService.getSurveysByUserId();
        return HttpResponse.okBuild(
                surveyList
        );
    }
    @DeleteMapping("/delete/{surveyId}")
    @Operation(summary = "질병리스트 삭제", description = "건강설문을 삭제합니다.")
    public HttpResponse<String> deleteSurvey(@PathVariable Long surveyId) {
        surveyService.deleteSurvey(surveyId);
        return HttpResponse.okBuild("건강설문 삭제가 완료되었습니다.");
    }
}
