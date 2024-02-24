package com.swig.zigzzang.member.service;

import com.swig.zigzzang.email.dto.EmailResponseDto;
import com.swig.zigzzang.email.service.EmailService;
import com.swig.zigzzang.global.exception.HttpExceptionCode;
import com.swig.zigzzang.global.redis.RedisService;
import com.swig.zigzzang.member.domain.Member;
import com.swig.zigzzang.member.dto.MemberJoinRequest;
import com.swig.zigzzang.member.exception.MemberExistException;
import com.swig.zigzzang.member.exception.NickNameAlreadyExistException;
import com.swig.zigzzang.member.exception.UserIdAlreadyExistException;
import com.swig.zigzzang.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Optional;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Value("${spring.mail.auth-code-expiration-millis}")
    private long authCodeExpirationMillis;
    private static final String AUTH_CODE_PREFIX = "AuthCode ";
    private final EmailService mailService;
    private final RedisService redisService;



    public Member save(MemberJoinRequest memberJoinRequest) {
        Member member = memberJoinRequest.toEntity();

        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        Optional<Member> userId = memberRepository.findByUserId(member.getUserId());
        if (userId.isPresent()) {
            throw new UserIdAlreadyExistException();
        }
        Optional<Member> nickname = memberRepository.findByNickname(member.getNickname());
        if (nickname.isPresent()) {
            throw new NickNameAlreadyExistException();
        }

        Member savedmember = memberRepository.save(member);


        return savedmember;
    }
    public void sendCodeToEmail(String toEmail) {
        this.checkDuplicatedEmail(toEmail);
        String title = "직짱건강 이메일 인증 번호";
        String authCode = this.createCode();
        mailService.sendEmail(toEmail, title, authCode);
        redisService.setValues(AUTH_CODE_PREFIX + toEmail,
                authCode, Duration.ofMillis(this.authCodeExpirationMillis));
    }

    private void checkDuplicatedEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) {
            throw new MemberExistException(HttpExceptionCode.MEMBER_EXISTS);
        }
    }

    private String createCode() {
        int lenth = 6;
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < lenth; i++) {
                builder.append(random.nextInt(10));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new MemberExistException(HttpExceptionCode.MEMBER_EXISTS);
        }
    }

    public Boolean verifiedCode(String email, String authCode) {
        this.checkDuplicatedEmail(email);
        String redisAuthCode = redisService.getValues(AUTH_CODE_PREFIX + email);
        //현재 redis 저장값과 비교(redis 저장코드는 이메일 송신시마다 overwrite)
        boolean authResult = redisService.checkExistsValue(redisAuthCode) && redisAuthCode.equals(authCode);

        return authResult;
    }

}
