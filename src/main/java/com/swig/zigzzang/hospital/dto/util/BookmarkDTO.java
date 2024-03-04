package com.swig.zigzzang.hospital.dto.util;


import com.swig.zigzzang.hospital.domain.Hospital;
import com.swig.zigzzang.hospital.domain.MemberHospital;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class BookmarkDTO {

    String googleMapId;

    LocalDate bookmarkDate;


    public BookmarkDTO(MemberHospital memberHospital) {
        this.googleMapId = memberHospital.getHospital().getGoogleMapId();
        this.bookmarkDate = LocalDate.from(memberHospital.getModifyDate());

    }

    public static List<BookmarkDTO> of(List<MemberHospital> hospitalList) {
        return hospitalList.stream()
                .map(BookmarkDTO::new)
                .collect(Collectors.toList());
    }
}
