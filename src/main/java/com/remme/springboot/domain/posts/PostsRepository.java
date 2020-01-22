package com.remme.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// 보통 말하는 Dao
// JPA에서는  Repository라고 부르며 인터페이스로 생성
// 단순히 인터페이스를 생성 한 후 JpaRepository를 상속하면 기본적인 crud 메서드가 자동생성된다.
// 주의할 점은 Entity 클래스와 함께 위치해야한다.
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
