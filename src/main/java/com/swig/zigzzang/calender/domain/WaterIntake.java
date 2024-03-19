package com.swig.zigzzang.calender.domain;


import com.swig.zigzzang.calender.dto.request.Supplement.SupplementAchieveUpdateRequest;
import com.swig.zigzzang.calender.dto.request.WaterIntake.WaterIntakeAchieveUpdateRequest;
import com.swig.zigzzang.calender.dto.request.WaterIntake.WaterIntakeUpdateRequest;
import com.swig.zigzzang.global.converter.BooleanArrayToStringConverter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class WaterIntake { // 물 섭취

    @Id
    @Column(name = "waterIntake_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long waterIntakeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calender_id")
    private Calender calender;

    @Column
    private Long requirement; // 물 목표 섭취량

    @Column
    private Long capacity; // 물 1회 섭취량

    @Column
    private Long frequency; // 물 하루 섭취 횟수

    @Column
    private String calenderDate; // 캘린더 날짜

    @Convert(converter = BooleanArrayToStringConverter.class)
    private Boolean[] achieveArray; // 달성 여부 배열
    
    
    // 정보 수정 메서드
    public void updateEntity(WaterIntakeUpdateRequest waterIntakeUpdateRequest){
        this.requirement = waterIntakeUpdateRequest.waterRequirement();
        this.capacity = waterIntakeUpdateRequest.waterCapacity();
        this.frequency = waterIntakeUpdateRequest.waterFrequency();
    }

    // 달성도 업데이트 메서드
    public void updateAchieveEntity(WaterIntakeAchieveUpdateRequest waterIntakeAchieveUpdateRequest) {
        this.achieveArray = waterIntakeAchieveUpdateRequest.achieveArray();
    }
}
