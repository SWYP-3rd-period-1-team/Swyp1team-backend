package com.swig.zigzzang.calender.domain;


import com.swig.zigzzang.calender.dto.request.Schedule.ScheduleUpdateRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Schedule { // 일정

    @Id
    @Column(name = "schedule_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calender_id")
    private Calender calender;

    @Column
    private String name; // 스케쥴 이름

    @Column
    private String date; // 스케쥴 날짜

    @Column
    private String time; // 스케쥴 시간



    // 정보 수정 메서드
    public void updateEntity(ScheduleUpdateRequest scheduleUpdateRequest){
        this.name = scheduleUpdateRequest.scheduleName();
        this.date = scheduleUpdateRequest.scheduleDate().toString();
        this.time = scheduleUpdateRequest.scheduleTime().toString();
    }
}
