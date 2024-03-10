package com.swig.zigzzang.calender.domain;


import com.swig.zigzzang.calender.dto.request.WaterIntake.WaterIntakeUpdateRequest;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calender_id")
    private Calender calender;

    @Column
    private Long requirement; // 물 목표 섭취량

    @Column
    private Long capacity; // 물 1회 섭취량

    @Column
    private Long frequency; // 물 하루 섭취 횟수

    @Column
    private Long achievement; // 달성도

    @Column
    private String calenderDate; // 캘린더 날짜
    
    
    // 정보 수정 메서드
    public void updateEntity(WaterIntakeUpdateRequest waterIntakeUpdateRequest){
        this.requirement = waterIntakeUpdateRequest.waterRequirement();
        this.capacity = waterIntakeUpdateRequest.waterCapacity();
        this.frequency = waterIntakeUpdateRequest.waterFrequency();
    }
}
