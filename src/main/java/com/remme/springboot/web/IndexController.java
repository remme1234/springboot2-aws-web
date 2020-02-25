package com.remme.springboot.web;

import com.remme.springboot.config.auth.LoginUser;
import com.remme.springboot.config.auth.dto.SessionUser;
import com.remme.springboot.service.posts.PostsService;
import com.remme.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    // 머스테치 덕분에 컨트롤러에서 문자열을 반환할때 앞의 경로와 뒤의 확장자는 자동으로 지정됨
    // 앞의경로는 /src/main/resource/templates/index.mustache로 view Resolver가 처리
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        // postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달
        model.addAttribute("posts", postsService.findAllDesc());
        // 미리 작성한 CustomOAuth2UserService에서 로그인 성공시 세션에 SessionUser를 저장하도록 구성
        // 즉 성공시 httpSession.getAttribute("user")에서 값을 가져올 수 있다

        // 아래는 위에 @LoginUser 어노테이션이 추가되면서 제외된 부분
       /* SessionUser user = (SessionUser) httpSession.getAttribute("user");*/
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
