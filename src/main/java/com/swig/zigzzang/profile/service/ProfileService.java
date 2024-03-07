package com.swig.zigzzang.profile.service;

import com.swig.zigzzang.member.domain.Member;
import com.swig.zigzzang.member.exception.MemberNotFoundException;
import com.swig.zigzzang.member.repository.MemberRepository;
import com.swig.zigzzang.member.service.MemberService;
import com.swig.zigzzang.profile.S3Service;
import com.swig.zigzzang.profile.dto.UploadImage;
import jakarta.transaction.Transactional;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final S3Service s3Service;
    private final MemberService memberService;
    private final MemberRepository memberRepository;


    public String uploadProfileImage(UploadImage imageDto) throws IOException {
        MultipartFile profileImage = imageDto.profileImage();

        String userId = memberService.getUsernameBySecurityContext();
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(MemberNotFoundException::new);


        String s3url = s3Service.saveProfileImage(userId, profileImage);

        member.setProfileimage(s3url);
        memberRepository.save(member);

        return s3url;
    }


    @Transactional
    public void deleteProfileImage() {
        String userId = memberService.getUsernameBySecurityContext();
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(MemberNotFoundException::new);

        if (member.getProfileimage() != null) {
            s3Service.deleteProfileImage(member);
            member.setProfileimage(null);
            memberRepository.save(member);
        }
    }


}
