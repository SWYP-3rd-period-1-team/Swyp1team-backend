package com.swig.zigzzang.calender.domain;


import com.swig.zigzzang.calender.dto.request.Supplement.SupplementAchieveUpdateRequest;
import com.swig.zigzzang.calender.dto.request.Supplement.SupplementUpdateRequest;
import com.swig.zigzzang.global.converter.BooleanArrayToStringConverter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Supplement { // 영양제

    @Id
    @Column(name = "supplement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplementId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calender_id")
    private Calender calender;

    @Column
    private String name; // 영양제 이름

    @Column
    private Long number; // 영양제 1회 섭취 개수

    @Column
    private Long frequency; // 영양제 1일 섭취 횟수

    @Column
    private String calenderDate; // 캘린더 날짜

    @Convert(converter = BooleanArrayToStringConverter.class)
    private Boolean[] achieveArray; // 달성 여부 배열


    // 수정 메서드
    public void updateEntity(SupplementUpdateRequest supplementUpdateRequest) {
        this.name = supplementUpdateRequest.supplementName();
        this.number = supplementUpdateRequest.supplementNumber();
        this.frequency = supplementUpdateRequest.supplementFrequency();
    }


    // 달성도 배열 업데이트 메서드
    public void updateAchieveEntity(SupplementAchieveUpdateRequest supplementAchieveUpdateRequest) {
        this.achieveArray = supplementAchieveUpdateRequest.achieveArray();
    }
}
