package org.zerock.j1.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.j1.domain.Board;
import org.zerock.j1.repository.search.BoardSearch;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch{

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

 


  
}
