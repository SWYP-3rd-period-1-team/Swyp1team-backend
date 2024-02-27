package com.swig.zigzzang.utill;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // Auditing(자동으로 값 매핑) 기능 추가
public abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    public LocalDateTime createdDate = LocalDateTime.now().plusHours(9);

    @LastModifiedDate
    public LocalDateTime updatedDate = LocalDateTime.now().plusHours(9);

}
