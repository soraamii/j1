package org.zerock.j1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "t_reply")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "board") // 쿼리가 두 번 날아가게 됨 -> board는 빼고 ToString
public class Reply {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long rno;

  private String replyText;

  private String replyFile;

  private String replyer;

  // 연관관계 vscode는 런타임 실행할 때 오류가 나므로 미리 어노테이션 걸어줘야 함
  // [규칙] fetch: 데이터를 가지고 옴, LAZY하게 가져온다는 건? 내가 필요한 순간까지는 쿼리를 날리지 않을 것이라는 걸 명시해줌
  // -> 필요 없는 쿼리를 한 번 더 날리는 것 방지
  // 연관관계를 걸 때는 LAZY를 걸어줘야 함!
  @ManyToOne(fetch = FetchType.LAZY) 
  private Board board;


  public void changeText(String text) {
    this.replyText = text;

  }

  public void changeFile(String fileName) {
    this.replyFile = fileName;

  }



}
