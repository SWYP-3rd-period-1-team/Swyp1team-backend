package com.swig.zigzzang.survey.domain;

import com.swig.zigzzang.member.domain.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "surveyId")
    private Long surveyId;

    @ManyToOne
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;

    @Column
    private String targetBody;

    @Column
    private String diagnosisPart;

    @Column
    private String presentedSymptom;

}
