package com.swig.zigzzang.profile.dto;

import org.springframework.web.multipart.MultipartFile;

public record ChangeProfileImageRequest(
        MultipartFile imageFile
) {
}
