package com.swig.zigzzang.hospital.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_id")
    private Long hospitalId;

    @Column
    private Long kakaoMapId;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "hospital_id")
    private Set<HospitalReview> hospitalReviews = new HashSet<>();

    // 추가 코드 생략
}