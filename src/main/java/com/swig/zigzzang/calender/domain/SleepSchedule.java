package com.swig.zigzzang.calender.domain;


import com.swig.zigzzang.calender.dto.request.SleepSchedule.SleepScheduleAchieveUpdateRequest;
import com.swig.zigzzang.calender.dto.request.SleepSchedule.SleepScheduleUpdateRequest;
import com.swig.zigzzang.calender.dto.request.Supplement.SupplementAchieveUpdateRequest;
import com.swig.zigzzang.global.converter.BooleanArrayToStringConverter;
import com.swig.zigzzang.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class SleepSchedule {

    @Id
    @Column(name = "sleepSchedule_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sleepScheduleId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calender_id")
    private Calender calender;

    @Column
    private Long period; // 수면 기간

    @Column
    private Long time; // 총 수면 시간

    @Column
    private String calenderDate; // 캘린더 날짜

    @Column
    private Boolean achieveArray; // 달성 여부 배열

    
    // 정보 수정 메서드
    public void updateEntity(SleepScheduleUpdateRequest sleepScheduleUpdateRequest){
        this.period = Long.valueOf(sleepScheduleUpdateRequest.sleepPeriod());
        this.time = sleepScheduleUpdateRequest.sleepTime();
    }

    // 달성도 배열 업데이트 메서드
    public void updateAchieveEntity(SleepScheduleAchieveUpdateRequest sleepScheduleAchieveUpdateRequest) {
        this.achieveArray = sleepScheduleAchieveUpdateRequest.achievement();
    }
}
