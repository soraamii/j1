package org.zerock.j1.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

// 추상 클래스
@MappedSuperclass // 테이블로 만들어지지 않게 하는 어노테이션
@Getter
@EntityListeners(value = { AuditingEntityListener.class }) // 감시, 감독
public abstract class BaseEntity {

  // 등록 시간
  @CreatedDate
  @Column(updatable = false) // 재실행 시에 테이블이 계속 변경되는 버그가 있음 -> update를 false로 막아줌
  private LocalDateTime regDate;

  // 수정 시간
  @LastModifiedDate
  private LocalDateTime modDate;

  

}
