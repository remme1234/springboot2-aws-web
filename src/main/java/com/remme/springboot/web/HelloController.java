package com.remme.springboot.web;

import com.remme.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


// JSON 을 반환하는 컨트롤로 만들어준다. 예전의 responseBody와 같다고 생각하면 된다.
@RestController
public class HelloController {

    // http method인 get의 요청을 받을 수 있는 api를 만들어준다.
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name
            ,@RequestParam("amount") int amount) {
        return new HelloResponseDto(name,amount);
    }
}
