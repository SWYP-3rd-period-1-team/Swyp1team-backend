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
    @Operation
    public String main() {
        String username= SecurityContextHolder.getContext().getAuthentication()
                .getName();


        return "현재 접속자 아이디 : "+username;
    }
}