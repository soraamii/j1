package org.zerock.j1.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.j1.domain.Todo;
import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.dto.TodoDTO;
import org.zerock.j1.repository.TodoRepository;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class TodoServiceImple implements TodoService {
  
  private final TodoRepository todoRepository;

  private final ModelMapper modelMapper;
  
  // 목록 조회
  @Override
  public PageResponseDTO<TodoDTO> getList() {
   
    Pageable pageable = 
      PageRequest.of(0, 20, Sort.by("tno").descending());

    Page<Todo> result = todoRepository.findAll(pageable);

    List<TodoDTO> dtoList = result.getContent().stream()
      .map(todo -> modelMapper.map(todo, TodoDTO.class))
      .collect(Collectors.toList());

    PageResponseDTO<TodoDTO> reponse = new PageResponseDTO<>();

    reponse.setDtoList(dtoList);

    return reponse;

  }

  // 등록
  @Override
  public TodoDTO register(TodoDTO dto) {
    
    Todo entity = modelMapper.map(dto, Todo.class);
    
    Todo result = todoRepository.save(entity);

    return modelMapper.map(result, TodoDTO.class);

  }
  
}
