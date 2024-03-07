package com.swig.zigzzang.profile.controller;

import com.swig.zigzzang.global.response.HttpResponse;
import com.swig.zigzzang.profile.dto.UploadImage;
import com.swig.zigzzang.profile.exception.ImageUploadException;
import com.swig.zigzzang.profile.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/upload")
    @Operation(summary = "프로필 이미지 업로드", description = "프로필 이미지를 업로드합니다.")

    public HttpResponse<String> uploadProfileImage(UploadImage imageDto) {
        try {
            String imageUrl = profileService.uploadProfileImage(imageDto);
            return HttpResponse.okBuild(
                    imageUrl
            );
        } catch (IOException e) {
            throw new ImageUploadException();
        }
    }
}
