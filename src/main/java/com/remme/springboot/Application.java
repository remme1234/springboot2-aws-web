package com.remme.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA auditing 활성화 !!
// 방금 생성한 application 클래스는 main 클래스가 된다.
// @SpringBootApplication 으로 인해 스프링부트의 자동설정, 스프링 bean읽기와 생성을 모두 자동으로 설정
// 그리고 항상 @SpringBootApplication이 있는 위치부터 설정을 읽어나가기 때문에 최상단에 위치해야 한다.
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // main메서드 안에 있는 .run으로 인해 내장 was를 실행한다.
        SpringApplication.run(Application.class, args);
    }
}
