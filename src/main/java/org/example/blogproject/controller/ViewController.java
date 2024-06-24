package org.example.blogproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.blogproject.domain.auth.User;
import org.example.blogproject.service.auth.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ViewController {
    private final UserService userService;

    //회원가입 - signUp.html 연결
    @GetMapping("/userreg")
    public String signUp(Model model) {
        model.addAttribute("user", new User());
        return "auth/signUp";
    }

    //회원가입 완료 - welcome.html 연결
    @GetMapping("/welcome")
    public String welcome() {
        return "auth/welcome";
    }


    //로그인 - logIn.html 연결
    @GetMapping("/login")
    public String login() {
        return "auth/logIn";
    }

    //로그인 완료 - blog.html 연결
    @GetMapping("/@{username}")
    public String userInfo(@PathVariable("username") String username, Model model) {
        User user = userService.userInfoByUsername(username);
        model.addAttribute("user", user);
        return "auth/blog";
    }
}
