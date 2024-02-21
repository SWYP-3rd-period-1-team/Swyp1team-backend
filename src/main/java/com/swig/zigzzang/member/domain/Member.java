package com.swig.zigzzang.member.domain;

import com.swig.zigzzang.MemberHospital.MemberHospital;
import com.swig.zigzzang.survey.domain.Survey;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column
    private String nickname;

    @Column
    private String userId;

    @Column
    private String password;

    @Column
    private String email;

    @OneToMany(mappedBy = "member")
    private List<MemberHospital> memberHospitals = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Survey> surveys;
}
