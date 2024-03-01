package com.swig.zigzzang.hospital.dto.util;


import com.swig.zigzzang.hospital.domain.Hospital;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class BookmarkDTO {

    String googleMapId;


    public BookmarkDTO(Hospital hospital) {
        this.googleMapId = hospital.getGoogleMapId();
    }

    public static List<BookmarkDTO> of(List<Hospital> hospitalList) {
        return hospitalList.stream()
                .map(BookmarkDTO::new)
                .collect(Collectors.toList());
    }
}
