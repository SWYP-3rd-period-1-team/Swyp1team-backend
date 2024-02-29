package com.swig.zigzzang.hospital.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
public record HospitalBookmarkSaveResponse(
        String message
){
    public static HospitalBookmarkSaveResponse of(Boolean bookmark) {
        if (bookmark) {
            return new HospitalBookmarkSaveResponse("병원 북마크가 등록되었습니다.");
        }
        else {
            return new HospitalBookmarkSaveResponse("병원 북마크가 해제되었습니다.");
        }
    }
}
