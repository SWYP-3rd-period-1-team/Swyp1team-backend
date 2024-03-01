package com.swig.zigzzang.hospital.dto.response;

import com.swig.zigzzang.hospital.dto.util.HospitalCommentDTO;
import lombok.Builder;

import java.util.List;

@Builder
public record HospitalDetailsResponse(
        List<HospitalCommentDTO> commentDTOList
) {
    public static HospitalDetailsResponse of(List<HospitalCommentDTO> commentList) {
            return HospitalDetailsResponse.builder()
                    .commentDTOList(commentList)
                    .build();
    }
}
