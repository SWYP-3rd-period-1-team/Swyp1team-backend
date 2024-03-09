package com.swig.zigzzang.calender.dto.util;

import com.swig.zigzzang.calender.domain.Supplement;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class SupplementDTO {

    String supplementName; // 영양제 이름

    Long supplementNumber;// 영양제 1회 섭취량

    Long supplementFrequency; // 영양제 1일 섭취 횟수

    Long achievement; // 진행률


    public static SupplementDTO of(Supplement supplement) {
        return SupplementDTO.builder()
                .supplementName(supplement.getName())
                .supplementNumber(supplement.getNumber())
                .supplementFrequency(supplement.getFrequency())
                .build();
    }

    // entityList -> dtoList
    public static List<SupplementDTO> ofList(List<Supplement> supplementList) {
        return supplementList.stream()
                .map(SupplementDTO::of)
                .collect(Collectors.toList());
    }

}
