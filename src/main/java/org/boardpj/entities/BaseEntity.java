package org.boardpj.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass //상위클래스 호환될수 있는 공통 속성
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity { //개별적으로 클래스 객체를 생성하지 않아서 abstract로 써줌
 @CreatedDate
 @Column(updatable = false)
    private LocalDateTime createAt;
 @LastModifiedDate
 @Column(insertable = false)
 private LocalDateTime modifiedAt;
}
