package org.zerock.j1.service;


import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.dto.TodoDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface TodoService {

  // 목록 조회
  PageResponseDTO<TodoDTO> getList();

  // 등록
  TodoDTO register(TodoDTO dto);
  
}
