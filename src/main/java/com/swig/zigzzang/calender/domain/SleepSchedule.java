package com.swig.zigzzang.calender.domain;


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
    private Long achievement; // 달성도

    @Column
    private String calenderDate; // 캘린더 날짜

}
