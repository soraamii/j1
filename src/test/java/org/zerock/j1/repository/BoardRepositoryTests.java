package org.zerock.j1.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.zerock.j1.domain.Board;
import org.zerock.j1.dto.BoardListRcntDTO;
import org.zerock.j1.dto.PageRequestDTO;
import org.zerock.j1.dto.PageResponseDTO;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

  @Autowired
  private BoardRepository boardRepository;

  // 등록
  @Test
  public void testInsert() {

    for(int i = 0; i < 100; i++) {

    Board board = Board.builder().title("Sample Title" + i).content("Sample Content" + i).writer("User00" + (i%10)).build();

    boardRepository.save(board);

    }


  }

  //조회
  @Test
  public void testRead() {

    Long tno = 1L;

    Optional<Board> result = boardRepository.findById(1L);

    log.info("---------------------------------------"); 
    // 지연로딩(Lazy Loading) -> transactional 걸어줘야 함 -> getOne() 같은 건 사용하지 말고 findBy를 사용해야 함(findBy는 즉시(eager)로딩)

    Board board = result.orElseThrow();

    log.info(board);
  }

  // 수정
  @Test
  public void testUpdate() {

    Long tno = 1L; // bno 값만 사용해서 업데이트하면 다른 컬럼들이 비워짐 -> 대책: @Query

    Optional<Board> result = boardRepository.findById(1L);

    Board board = result.orElseThrow(); // 조회를 하지 않으면 예외를 던져야 함

    board.changeTitle("Update Title");

    boardRepository.save(board);

  }

  // 쿼리 메소드 테스트 - 타이틀에 1이 들어가는 데이터 조회
  @Test
  public void testQuery1() {

    // 쿼리 메소드는 복잡한 쿼리문을 만들어 내기 어렵기 때문에 많이 사용되지 않음
    java.util.List<Board> list = boardRepository.findByTitleContaining("1");

    log.info("---------------------------------------------------------");
    log.info(list.size());
    log.info(list);

  }

  // 쿼리 메소드 테스트 - 콘텐츠에 1이 들어가는 데이터 조회
  @Test
  public void testQuery2() {

    Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

    Page<Board> result = boardRepository.findByContentContaining("1", pageable);

    log.info("---------------------------------------------------------");
    log.info(result);

  }

  // @Query 사용해서 조회 테스트
  @Test
  public void testQuery1_1() {

    java.util.List<Board> list = boardRepository.listTitle("1");

    log.info("---------------------------------------------------------");
    log.info(list.size());
    log.info(list);

  }

  // @Query 사용해서 조회 테스트
  @Test
  public void testQuery1_2() {

    java.util.List<Object[]> list = boardRepository.listTitle2("1");

    log.info("---------------------------------------------------------");
    log.info(list.size());
    
    list.forEach(arr -> log.info(Arrays.toString(arr)));

  }

  // @Query 사용해서 조회 테스트
  @Test
  public void testQuery1_3() {

    Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

    Page<Object[]> result = boardRepository.listTitle2("1", pageable);

    log.info(result);

  }

  // @Query 사용해서 수정 테스트
  @Commit // test 코드에서 modify는 commit 해줘야 함 아니면 rollback됨
  @Transactional 
  @Test
  public void testModify() {
    Long bno = 100L;

    String title = "Modified Title";

    int count  = boardRepository.modifyTitle(title, bno);

    log.info("=====================" + count);
  }

  // NativeQuery 테스트
  @Test
  public void testNative() {

    List<Object[]> result = boardRepository.listNative();

    result.forEach(arr -> log.info(Arrays.toString(arr)));
  }




  @Test
  public void testSearch1() {

    Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

    Page<Board> result = boardRepository.search1(null,"1",pageable);

    log.info(result.getTotalElements());

    result.get().forEach(b -> log.info(b));
  }
  

  @Test
  public void testListWithRcnt() {

    List<Object[]> result = boardRepository.getListWithRcnt();

    for(Object[] result2 : result) {
      log.info((Arrays.toString(result2)));
    }

  }

  @Test
  public void testListWithRcntSearch() {

    Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

    boardRepository.searchWithRcnt("tcw", "1", pageable);

  }

  @Test
  public void test0706_1() {

    PageRequestDTO pageRequest = new PageRequestDTO();

    boardRepository.searchDTORcnt(pageRequest);

    PageResponseDTO<BoardListRcntDTO> responseDTO =
      boardRepository.searchDTORcnt(pageRequest);

    log.info(responseDTO);

  }


}
