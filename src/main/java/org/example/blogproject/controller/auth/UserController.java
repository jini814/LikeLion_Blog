package org.example.blogproject.controller.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.blogproject.domain.auth.User;
import org.example.blogproject.exception.BlogException;
import org.example.blogproject.service.auth.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //회원가입 form 제공
    @GetMapping("/userregform")
    public String signUpForm() {
        return "/userreg";
    }

    //회원가입
    @PostMapping("/userreg")
    public String signUp(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        try {
            userService.signUp(user);
            redirectAttributes.addFlashAttribute("message", "회원 가입이 성공적으로 완료되었습니다.");
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:/welcome";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "error";
        }
    }

    //로그인 form 제공
    @GetMapping("/loginform")
    public String logInForm() {
        return "login";
    }

    //로그인
    @PostMapping("/login")
    public String logIn(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,
                        HttpServletResponse response) {
        try {
            User user = userService.logIn(username, password);

            Cookie cookie = new Cookie("user", user.getUsername());
            cookie.setPath("/"); //쿠키 경로 설정
            cookie.setHttpOnly(true); //HttpOnly설정
            cookie.setMaxAge(24 * 60 * 60); //쿠키 유효 기간 : 1일
            response.addCookie(cookie); //클라이언트에게 쿠키 전송

            model.addAttribute("username", username);
            return "redirect:/@" + username;
        } catch (BlogException e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    //로그아웃
    @GetMapping("/logout")
    public String logOut(HttpServletResponse response) {
        Cookie cookie = new Cookie("user", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/login";
    }
}
