package com.swig.zigzzang.hospital;

import com.swig.zigzzang.MemberHospital.MemberHospital;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_id")
    private Long hospitalId;

    @Column
    private Long kakaomapId;

    @OneToMany(mappedBy = "hospital")
    private List<MemberHospital> memberHospitals = new ArrayList<>();

    // 추가 코드 생략
}