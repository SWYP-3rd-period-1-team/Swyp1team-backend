package com.swig.zigzzang.profile.dto;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;
@Builder
public record UploadImage(
        MultipartFile profileImage
) {
}
