package com.swig.zigzzang.hospital.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Hospital  { // 병원 Entity

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hopital_id")
    private Long hospitalId;

    @Column
    private String googleMapId;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "hospital_id")
    private Set<HospitalComment> hospitalCommentList = new HashSet<>();
}
