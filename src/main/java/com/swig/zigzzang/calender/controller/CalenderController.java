package com.swig.zigzzang.calender.controller;

import com.swig.zigzzang.calender.dto.response.Calender.CalenderListResponse;
import com.swig.zigzzang.calender.dto.util.MyCalenderDTO;
import com.swig.zigzzang.calender.service.CalenderService;
import com.swig.zigzzang.global.response.HttpResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Tag(name = "CalenderController", description = "캘린더 관련 api")
@RestController
@RequestMapping("/api/calenders")
@RequiredArgsConstructor
public class CalenderController {

    private final CalenderService calenderService;

    @Operation(summary = "날짜별 기본 캘린더 목록 api", description = "날짜별 로그인 유저 기본 캘린더에 저장된 물,영양제,수면,일정 정보를 불러옵니다.")
    @GetMapping("/myCalenders")
    public HttpResponse<CalenderListResponse> calenderMylist(@RequestParam("calenderDate") String calenderDate) {

        String loginUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        MyCalenderDTO result = calenderService.myListCalender(calenderDate,loginUserId);

        return HttpResponse.okBuild(
                CalenderListResponse.of(result)
        );
    }
}
