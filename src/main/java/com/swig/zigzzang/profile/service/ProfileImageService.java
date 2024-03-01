package com.swig.zigzzang.profile.service;

import org.springframework.web.multipart.MultipartFile;

public interface ProfileImageService {
    String uploadProfileImage(MultipartFile imageFile, String userId);
    void deleteProfileImage(String imageUrl);
}
