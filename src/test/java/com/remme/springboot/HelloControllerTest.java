package com.remme.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// 테스트를 진행할 때 junit에 내장된 실행자 외에 다른 실행자를 실행시킨다.
// 여기서는 SpringRunner라는 스프링 실행자를 사용
// 즉 , 스프링 boot test와 junit 사이에 연결자 역할

@RunWith(SpringRunner.class)
// web(spring mvc)에 집중할 수 있는 어노테이션
@WebMvcTest
public class HelloControllerTest {

    // 스프링이 관리하는 bean을 주입받는다
    @Autowired
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                // mvc.perform의 결과를 검증, 우리가 아는 200, 404, 500 등의 상태를 검증한다
                .andExpect(status().isOk())
                // 응답 본문 내용을 검증한다. controller에서 "hello"를 return 하기 때문에 이 값이 맞는지 검증
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                        // api테스트 할 때 사용될 요청 파라미터를 설정
                        // 단 값은 String만 허용된다.
                        .param("name",name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                // json응답값을 필드별로 검증할 수 있는 메소드
                // $를 기준으로 필드명을 명시한다. 여기서는 name과 amount를 검증하므로 $.name과 $.amout로 검증
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount",is(amount)));
    }
}