package org.example.web;

import jakarta.servlet.http.HttpSession; // 추가
import org.example.config.auth.LoginUser;
import org.example.config.auth.dto.SessionUser;
import org.example.service.posts.PostsService;
import org.example.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession; // 세션 정보를 가져오기 위해 주입

    @GetMapping("/")
    public String index(Model model) { // @LoginUser를 잠시 제거
        model.addAttribute("posts", postsService.findAllDesc());

        // 세션에서 직접 "user"를 꺼내봅니다.
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            System.out.println("세션 유저 이름: " + user.getName()); // 콘솔에 찍히는지 확인
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // login.mustache를 보여줌
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