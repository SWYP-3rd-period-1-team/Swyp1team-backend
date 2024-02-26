package com.swig.zigzzang.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swig.zigzzang.global.exception.HttpExceptionCode;
import com.swig.zigzzang.member.domain.Member;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springdoc.api.ErrorMessage;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {

        this.jwtUtil = jwtUtil;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;

        }

        System.out.println("authorization now");
        String token = authorization.split(" ")[1];
        try {
            jwtUtil.validateToken(token);
        } catch (JwtException e) {
            String message = e.getMessage();
            if(HttpExceptionCode.EXPIRED_TOKEN.getMessage().equals(message))
            setResponse(response,HttpExceptionCode.EXPIRED_TOKEN);
            return;
        }

        String username = jwtUtil.getUserId(token);
        String password = jwtUtil.getPassword(token);

        Member member = Member.builder()
                .userId(username)

                .password(password)
                .build();
        CustomUserDetails customUserDetails = new CustomUserDetails(member);

        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null,
                customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
    private void setResponse(HttpServletResponse response, HttpExceptionCode errorMessage) throws RuntimeException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(errorMessage.getHttpStatus().value());
        response.getWriter().print(errorMessage.getMessage());
    }
}
