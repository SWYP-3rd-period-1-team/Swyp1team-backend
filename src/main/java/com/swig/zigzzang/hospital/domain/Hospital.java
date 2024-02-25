package com.swig.zigzzang.hospital.domain;

import jakarta.persistence.*;
import lombok.*;

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
    private Long googleMapId;

}
