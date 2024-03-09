package com.swig.zigzzang.member.service;

import com.swig.zigzzang.calender.domain.Calender;
import com.swig.zigzzang.calender.repository.CalenderRepository;
import com.swig.zigzzang.email.dto.EmailResponseDto;
import com.swig.zigzzang.email.service.EmailService;
import com.swig.zigzzang.global.exception.HttpExceptionCode;
import com.swig.zigzzang.global.exception.custom.security.IncorrectRefreshTokenException;
import com.swig.zigzzang.global.exception.custom.security.SecurityJwtNotFoundException;
import com.swig.zigzzang.global.redis.RedisService;
import com.swig.zigzzang.global.security.JWTUtil;
import com.swig.zigzzang.member.domain.Member;
import com.swig.zigzzang.member.dto.ChangeNicknameRequest;
import com.swig.zigzzang.member.dto.ChangePasswordRequest;
import com.swig.zigzzang.member.dto.MemberJoinRequest;
import com.swig.zigzzang.member.exception.EmailCodeFailedException;
import com.swig.zigzzang.member.exception.EmailVerificationException;
import com.swig.zigzzang.member.exception.IncorrectPasswordException;
import com.swig.zigzzang.member.exception.MemberExistException;
import com.swig.zigzzang.member.exception.MemberNotFoundException;
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
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final CalenderRepository calenderRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Value("${spring.mail.auth-code-expiration-millis}")
    private long authCodeExpirationMillis;
    private static final String AUTH_CODE_PREFIX = "AuthCode ";
    private final EmailService mailService;
    private final RedisService redisService;
    private final JWTUtil jwtUtil;


    public Member save(MemberJoinRequest memberJoinRequest) {
        Member member = memberJoinRequest.toEntity();

        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        memberRepository.findByUserId(member.getUserId())
                .ifPresent(m -> {
                    throw new UserIdAlreadyExistException();
                });

        memberRepository.findByNickname(member.getNickname())
                .ifPresent(m -> {
                    throw new NickNameAlreadyExistException();
                });

        checkDuplicatedEmail(member.getEmail());

        Member savedmember = memberRepository.save(member);

        Calender saveEntity = memberJoinRequest.toCalenderEntity(savedmember);

        calenderRepository.save(saveEntity); // 회원 가입 시 기본 캘린더 저장

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
        memberRepository.findByEmail(email)
                .ifPresent(m -> {
                    throw new MemberExistException(HttpExceptionCode.EMAIL_ALREADY_EXIST);
                });
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
        if (!authResult) {
            throw new EmailCodeFailedException();
        }

        return authResult;
    }

    public String reisuueAccessToken(String encryptedRefreshToken) {
        isTokenPresent(encryptedRefreshToken);
        //앞의 Bearer 삭제후 순수 RT 추출
        String pureRefreshToken = getBearerSubstring(encryptedRefreshToken);
        //redis에서 해당 키 검색해서 해당 토큰에 대응하는 key 추출
        String userId = redisService.getValues(pureRefreshToken);
        //RT가 redis에 저장된 값이랑 일치하는지 확인
        if (!redisService.checkExistsValue(userId)) {
            throw new IncorrectRefreshTokenException();
        }

        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(MemberNotFoundException::new);

        //jwt AT 생성
        String newAccessToken = getAccessToken(member);

        return "Bearer " + newAccessToken;
    }

    public String reissueRefreshToken(String encryptedRefreshToken) {
        isTokenPresent(encryptedRefreshToken);
        //앞의 Bearer 삭제후 순수 RT 추출
        String pureRefreshToken = getBearerSubstring(encryptedRefreshToken);
        //redis에서 해당 키 검색해서 해당 토큰에 대응하는 key 추출
        String userId = redisService.getValues(pureRefreshToken);
        //RT가 redis에 저장된 값이랑 일치하는지 확인
        if (!redisService.checkExistsValue(userId)) {
            throw new IncorrectRefreshTokenException();
        }

        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(MemberNotFoundException::new);

        //jwt RT 생성(RTR 기법 도입)
        String newrefreshToken = getRefreshToken(member);
        //기존 RT 삭제
        redisService.deleteValues(encryptedRefreshToken);

        return "Bearer " + newrefreshToken;


    }


    public String logout(String encryptedRefreshToken) {
        isTokenPresent(encryptedRefreshToken);
        //RT가 레디스에 저장된값이랑 일치하는지 확인
        String userId = redisService.getValues(encryptedRefreshToken);
        if (!redisService.checkExistsValue(userId)) {
            throw new IncorrectRefreshTokenException();
        }
        //RT 를 레디스에서 삭제
        redisService.deleteValues(encryptedRefreshToken);

        String result = addToBlacklist(encryptedRefreshToken);
        return result;

    }

    private String addToBlacklist(String encryptedRefreshToken) {
        String blacklistKey = encryptedRefreshToken;

        redisService.setValues(blacklistKey, "blacklist", Duration.ofMillis(60 * 60 * 100L));
        return "blaklist " + blacklistKey;
    }

    private void isTokenPresent(String encryptedRefreshToken) {
        if (encryptedRefreshToken == null) {
            throw new SecurityJwtNotFoundException(HttpExceptionCode.JWT_NOT_FOUND);
        }
    }

    private static String getBearerSubstring(String encryptedRefreshToken) {
        return encryptedRefreshToken.substring(7);
    }

    private String getAccessToken(Member user) {
        return jwtUtil.createJwt(user.getUserId(), user.getPassword(), 60 * 60 * 100L);
    }

    private String getRefreshToken(Member user) {
        String refreshToken = jwtUtil.createRefreshToken(user.getUserId(), user.getPassword(), 86400000 * 7L);

        return refreshToken;
    }

    public String findIdByEmail(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException(HttpExceptionCode.EMAIL_USER_NOTFOUND));
        return member.getUserId();
    }

    public String findPassword(String userId, String email) {
        Member member = memberRepository.findByUserIdAndEmail(userId, email)
                .orElseThrow(() -> new MemberNotFoundException(HttpExceptionCode.EMAIL_USERID_USER_NOT));

        String newPassword = generateNewPassword();
        member.setPassword(bCryptPasswordEncoder.encode(newPassword));
        memberRepository.save(member);

        String title = "직짱건강 임시 비밀번호 발급";
        mailService.sendEmail(email, title, "새로운 비밀번호 : " + newPassword);

        return email;
    }

    private String generateNewPassword() {
        String randomString = RandomStringUtils.randomAlphanumeric(9);

        int randomIndex = (int) (Math.random() * 9);

        char randomNumber = (char) ('0' + (int) (Math.random() * 10));
        char[] newPasswordChars = randomString.toCharArray();
        newPasswordChars[randomIndex] = randomNumber;

        return new String(newPasswordChars);
    }

    public String getUsernameBySecurityContext() {
        return SecurityContextHolder.getContext().getAuthentication()
                .getName();
    }

    public String changePassword(ChangePasswordRequest changePasswordRequest) {
        String userId = getUsernameBySecurityContext();
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new MemberNotFoundException(HttpExceptionCode.USER_NOT_FOUND));

        String newpassword = changePasswordRequest.newPassword();
        String confirmpassowrd = changePasswordRequest.confirmPassword();
        if (!newpassword.equals(confirmpassowrd)) {
            throw new IncorrectPasswordException();
        }

        member.setPassword(bCryptPasswordEncoder.encode(newpassword));
        memberRepository.save(member);

        return newpassword;
    }

    public String changeNickname(ChangeNicknameRequest changeNicknameRequest) {
        String userId = getUsernameBySecurityContext();
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new MemberNotFoundException(HttpExceptionCode.USER_NOT_FOUND));

        Optional<Member> existingNickname = memberRepository.findByNickname(changeNicknameRequest.newNickname());
        if (existingNickname.isPresent()) {
            throw new NickNameAlreadyExistException();
        }
        String newNickname = changeNicknameRequest.newNickname();

        member.setNickname(changeNicknameRequest.newNickname());
        memberRepository.save(member);

        return newNickname;
    }

    public void updateProfileImage(String userId, String imageUrl) {
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(MemberNotFoundException::new);

        member.setProfileimage(imageUrl);
        memberRepository.save(member);
    }

    public Member findMemberByUsername(String username) {
        return memberRepository.findByUserId(username)
                .orElseThrow(() -> new MemberNotFoundException(HttpExceptionCode.USER_NOT_FOUND));
    }

    public void verifyEmail(String email) {
        if (!memberRepository.existsByEmail(email)) {
            throw new EmailVerificationException();
        }
    }
}
