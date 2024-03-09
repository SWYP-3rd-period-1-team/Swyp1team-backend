package com.swig.zigzzang.calender.domain;


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

    @Column
    private Long achievement; // 달성도


}
