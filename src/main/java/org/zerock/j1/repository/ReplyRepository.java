package org.zerock.j1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.j1.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long>{

  @Query("select r from Reply r where r.board.bno = :bno")
  Page<Reply> listBoard(@Param("bno") Long bno, Pageable pageable);

  // 댓글의 수
  @Query("select count(r) from Reply r where r.board.bno = :bno")
  long getCountBoard(@Param("bno") Long bno);

  
}
