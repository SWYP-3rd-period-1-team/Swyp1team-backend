package com.swig.zigzzang.calender.domain;


import com.swig.zigzzang.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Calender {

    @Id
    @Column(name = "calender_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long calenderId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "member_id")
    private Member member;
}
