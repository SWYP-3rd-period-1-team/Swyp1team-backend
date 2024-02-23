package com.swig.zigzzang.email.service;

import com.swig.zigzzang.email.exception.EmailNotSendException;
import com.swig.zigzzang.global.exception.HttpExceptionCode;
import com.swig.zigzzang.member.service.MemberService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;

    public void sendEmail(String toEmail,
                          String title,
                          String text) {
        SimpleMailMessage emailForm = createEmailForm(toEmail, title, text);
//        try {
            emailSender.send(emailForm);
//        } catch (RuntimeException e) {
//            System.out.println("e.getMessage() = " + e.getMessage());
//            log.info("MailService.sendEmail exception occur toEmail: {}, " +
//                    "title: {}, text: {}", toEmail, title, text);
//            throw new EmailNotSendException(HttpExceptionCode.UNABLE_TO_SEND_EMAIL);
//        }
    }

    // 발신할 이메일 데이터 세팅
    private SimpleMailMessage createEmailForm(String toEmail,
                                              String title,
                                              String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(title);
        message.setText(text);

        return message;
    }

}
