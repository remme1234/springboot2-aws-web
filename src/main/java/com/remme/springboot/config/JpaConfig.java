package com.remme.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// p.221 EnableJpa Auditing을 사용하기 위해선 최소 하나의 entitiy클래스가 필요한데 @WebMvcTest이다 보니 없기 때문에 만들어 준다.
// 여기서는 EnableJapAuditing을 추가한 내용
@Configuration
@EnableJpaAuditing // jpa auditing 활성화
public class JpaConfig {}

