package com.swig.zigzzang.calender.domain;


import com.swig.zigzzang.member.domain.Member;
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
    private Long achievement; // 1일 달성도


}
