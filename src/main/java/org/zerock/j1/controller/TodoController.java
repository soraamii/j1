package org.zerock.j1.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

  @GetMapping("/list")
  public PageResponseDTO<TodoDTO> list() {

    return todoService.getList();

  }

  //@RequestBody => json으로 들어온 데이터 반환

  @PostMapping("/")
  public TodoDTO register(@RequestBody TodoDTO todoDTO) {

    log.info("register......................");
    log.info(todoDTO);

    return todoService.register(todoDTO);

  }


  
}
