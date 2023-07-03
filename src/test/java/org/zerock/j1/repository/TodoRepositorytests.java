package org.zerock.j1.repository;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.j1.domain.Todo;
import org.zerock.j1.dto.TodoDTO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class TodoRepositorytests {

  @Autowired
  private ModelMapper modelMapper;
  
  @Autowired
  private TodoRepository todoRepository;

  @Test
  public void testRead() {

    Long tno = 100L;

    // Optional: null 값인지 아닌지 체크
    // (존재할 수도 있고, 존재하지 않을 수도 있는 상황 체크)
    Optional<Todo> result = todoRepository.findById(tno);

    // Optional 객체에 값이 존재하지 안흥ㄹ 경우 예외 발생
    Todo entity = result.orElseThrow();

    log.info("ENTITY..........................");
    log.info(entity);

    // Entity를 DTO로 변환
    TodoDTO dto = modelMapper.map(entity, TodoDTO.class);

    log.info(dto);

  }

  
  @Test
  public void testInsert() {
    
    // 1부터 100까지 반복 작업 수행
    // IntStream: 정수 값의 시퀀스(연속적인 값 또는 개체들의 순서)를 생성하고 처리
    IntStream.rangeClosed(1, 100).forEach(i -> {

      // Todo Entity 생성
      Todo todo = Todo.builder().title("title"+i).build();

      // Todo 객체를 DB에 저장
      Todo result = todoRepository.save(todo); // selectKey 필요 없음

      log.info(result);

    });

  }

  @Test
  public void testPaging() {

    // Pageable: 페이징 처리를 위한 인터페이스, 페이징 정보 포함
    // 0부터 10까지
    // Sort.by(): 정렬을 생성하는 정적 메서드 -> 정렬 조건 지정 가능
    Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());

    Page<Todo> result = todoRepository.findAll(pageable);

    log.info(result);

  }


}
