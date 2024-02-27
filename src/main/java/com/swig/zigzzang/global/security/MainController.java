package com.swig.zigzzang.global.security;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class MainController {
    @GetMapping("/userinfo")
    @Operation(summary = "사용자 정보 확인", description = "헤더에 jwt가 있을시, 현재 접속자를 확인합니다.")

    public String main() {
        String username= SecurityContextHolder.getContext().getAuthentication()
                .getName();


        return "현재 접속자 아이디 : "+username;
    }
}