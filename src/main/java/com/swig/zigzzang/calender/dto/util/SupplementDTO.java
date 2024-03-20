package com.swig.zigzzang.calender.dto.util;

import com.swig.zigzzang.calender.domain.Supplement;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class SupplementDTO {

    @Schema(description = "영양제 번호", nullable = false, example = "1")
    Long supplementId;

    @Schema(description = "영양제 이름", nullable = false, example = "영양제")
    String supplementName;

    @Schema(description = "영양제 1회 섭취량", nullable = false, example = "30")
    Long supplementNumber;

    @Schema(description = "영양제 1일 섭취 횟수", nullable = false, example = "3")
    Long supplementFrequency;

    @Schema(description = "캘린더 날짜", nullable = false, example = "2024-03-10")
    String calenderDate;

    @Schema(description = "달성도 boolean 가변 배열", nullable = false, example = "[true,false,true,false]")
    Boolean [] supplementAchieveArray;


    public static SupplementDTO of(Supplement supplement) {
        return SupplementDTO.builder()
                .supplementId(supplement.getSupplementId())
                .supplementName(supplement.getName())
                .supplementNumber(supplement.getNumber())
                .supplementFrequency(supplement.getFrequency())
                .calenderDate(supplement.getCalenderDate())
                .supplementAchieveArray(supplement.getAchieveArray())
                .build();
    }

    // entityList -> dtoList
    public static List<SupplementDTO> ofList(List<Supplement> supplementList) {
        return supplementList.stream()
                .map(SupplementDTO::of)
                .collect(Collectors.toList());
    }

}
