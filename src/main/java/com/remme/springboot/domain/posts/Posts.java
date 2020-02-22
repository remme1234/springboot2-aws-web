package com.remme.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 이하 두 라인의 어노테이션은 롬복의 어노테이션
// 클래스 내의 모든 필드 getter메서드 생성
@Getter
// 기본 생성자 자동 추가
@NoArgsConstructor
// jpa의 어노테이션
// 테이블과 링크될 클래스임을 나타냄
// 기본값으로 클래스의 카멜케이스 이름을 언더스코어네이밍으로 테이블이름을 매칭
// SalesManager.java >> sales_manager table
@Entity
// Posts 클래스는 실제 DB테이블과 매칭될 클래스, Entity 클래스라고 한다.
// JPA 를 사용하면 DB 데이터에 작업할 경우 실제 쿼리를 날리기보다는 이 클래스의 수정을 통해서 작업한다.
public class Posts extends BaseTimeEntitiy {

    // PK
    @Id
    // PK 생성 규칙, 스프링부트2.0 에서는 GenerationType.IDENTITIY 옵션을 추가해야만 auto_increment가 된다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 굳이 선언하지 않아도 해당 클래스의 필드는 모두 컬럼이된다. 기본값 외에 필요한 설정이 있으면 사용
    @Column(length = 500, nullable = false)
    private  String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    // 해당 클래스의 빌더 패턴 클래스 생성, 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함됨
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
