package org.zerock.j1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.j1.domain.Todo;

// JpaRepository -> Spring Data JPA에서 제공하는 인터페이스
// -> 데이터베이스 조작을 위한 여러 조작 메서드 사용 가능 
// ex)findAll(), findById(), save(), delete()
// JpaRepository<Entity 타입, 식별자 타입(PK)>
public interface TodoRepository extends JpaRepository<Todo, Long>{
  
}
