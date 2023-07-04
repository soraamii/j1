package org.zerock.j1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


// 모든 Entity는 Id(PK, 식별자)가 있어야 함
@Entity
@Table(name = "tbl_sample")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter

public class Sample {
  
  @Id
  private String keyCol;

  // 컬럼명이 되므로 DB 예약어에 걸리지 않게 조심해야 함
  private String first;

  private String last;

  private String zipCode;
  
  private String city;

}
