package org.zerock.j1.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.dto.TodoDTO;
import org.zerock.j1.service.TodoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
@CrossOrigin // CORS 해결 방법
@Log4j2
public class TodoController {

  private final TodoService todoService;

  // 목록
  @GetMapping("/list")
  public PageResponseDTO<TodoDTO> list() {

    return todoService.getList();

  }

  // 조회
  @GetMapping("/{tno}")
  public TodoDTO get(@PathVariable Long tno) {

    return todoService.getOne(tno);
  }

  //@RequestBody => json으로 들어온 데이터 반환
  // 등록
  @PostMapping("/")
  public TodoDTO register(@RequestBody TodoDTO todoDTO) {

    log.info("register......................");
    log.info(todoDTO);

    return todoService.register(todoDTO);

  }

  @DeleteMapping("/{tno}")
  public Map<String, String> delete(@PathVariable("tno") Long tno) {

    todoService.remove(tno);

    return Map.of("result", "success");
  }

  @PutMapping("/{tno}")
  public Map<String, String> update(@PathVariable("tno") Long tno, @RequestBody TodoDTO todoDTO) {

    todoDTO.setTno(tno); // 안전장치

    todoService.modify(todoDTO);

    return Map.of("result", "success");
  }


  
}
