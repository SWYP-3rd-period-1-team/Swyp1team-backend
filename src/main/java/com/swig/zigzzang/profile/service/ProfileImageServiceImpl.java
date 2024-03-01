package com.swig.zigzzang.profile.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
@RequiredArgsConstructor
public class ProfileImageServiceImpl implements ProfileImageService{

//    @Value("${s3.url}")
    private String s3BaseUrl;

    @Override
    public String uploadProfileImage(MultipartFile imageFile, String userId) {
        try {
            String imageUrl = s3BaseUrl + "/" + userId + "/profile-image.jpg";
            Path destination = Path.of(Objects.requireNonNull(imageUrl));
            Files.copy(imageFile.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

            return imageUrl;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("이미지 등록 실패");
        }
    }

    @Override
    public void deleteProfileImage(String imageUrl) {
    }
}
