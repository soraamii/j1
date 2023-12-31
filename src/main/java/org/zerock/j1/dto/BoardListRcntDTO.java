package org.zerock.j1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardListRcntDTO {

  private Long bno; // 게시글 번호
  private String title; // 제목
  private String writer; // 작성자
  private long replyCount; // 댓글수
  
}
