package org.zerock.j1.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.j1.domain.Board;
import org.zerock.j1.domain.Reply;
import org.zerock.j1.dto.ReplyPageRequestDTO;
import org.zerock.j1.service.ReplyService;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {

  @Autowired
  private ReplyRepository replyRepository;
  @Autowired
  private ReplyService replyService;

  // insert 관련 코드는 실행되어 데이터 생성되었으면 커맨드 처리하는 게 좋음
  @Test
  public void insert1() {

    Long bno = 100L;

    // 가짜 보드 객체
    Board board = Board.builder().bno(bno).build();

    Reply reply = Reply.builder()
      .replyText("Reply1")
      .replyer("replyer00")
      .board(board)
      .build();

    replyRepository.save(reply);
  }
  

  @Test
  public void testInsertDummies() {

    Long[] bnoArr = {99L, 97L, 84L, 82L, 70L};

    for (Long bno : bnoArr) {

      Board board = Board.builder().bno(bno).build();

      for(int i = 0; i < 50; i++) {

        Reply reply = Reply.builder()
          .replyText("Reply.."+bno+"--"+i)
          .replyer("replyer"+i)
          .board(board)
          .build();

         replyRepository.save(reply);

      }
    } // end for
  }

  // no session이 뜨면 @Transactional이 필요함
  @Test
  public void testListBoard() {

    Long bno = 99L;

    Pageable pageable = PageRequest.of(0, 10, Sort.by("rno").ascending());

    Page<Reply> result = replyRepository.listBoard(bno, pageable);

    result.get().forEach(r -> log.info(r));

  }

  @Test
  public void testCount() {

    Long bno = 99L;

    Long count = replyRepository.getCountBoard(bno);

    log.info("count: " + count);

  }

  @Test
  public void testListLast() {

    ReplyPageRequestDTO requestDTO = ReplyPageRequestDTO.builder().bno(99L).last(true).build();

    log.info(replyService.list(requestDTO));



  }


}
