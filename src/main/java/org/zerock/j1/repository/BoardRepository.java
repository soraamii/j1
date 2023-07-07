package org.zerock.j1.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.j1.domain.Board;
import org.zerock.j1.dto.BoardReadDTO;
import org.zerock.j1.repository.search.BoardSearch;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch{

  // 결과물을 BoardReadDTO 타입으로 바꿈
  @Query("select b from Board b where b.bno = :bno")
  BoardReadDTO readOne(@Param("bno") Long bno);

  List<Board> findByTitleContaining(String title);

   // 메소드 이름을 fingBy로 만들면 쿼리 메소드로 인식함

  @Query("select b from Board b where b.title like %:title%")
  List<Board> listTitle(@Param("title") String title);

  @Query("select b.bno, b.title from Board b where b.title like %:title%")
  List<Object[]> listTitle2(@Param("title") String title);

  @Query("select b.bno, b.title from Board b where b.title like %:title%")
  Page<Object[]> listTitle2(@Param("title") String title, Pageable pageable);

  @Modifying
  @Query("update Board b set b.title = :title where b.bno = :bno")
  int modifyTitle(@Param("title") String title, @Param("bno") Long bno);


  Page<Board> findByContentContaining(String content, Pageable pageable);


  // JSQL로 사용X SQL을 직접 정의하여 사용
  @Query(value = "select * from t_board", nativeQuery = true)
  List<Object[]> listNative();

  // 클래스를 기준으로 짜줘야 함
  // 정적인 코드 -> 검색X -> QueryDsl로 뽑아야 함
  @Query("select b.bno, b.title, b.writer, count(r) from Board b left outer join Reply r on r.board = b group by b order by b.bno desc")
  List<Object[]> getListWithRcnt();
 


  
}
