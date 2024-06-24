package org.example.blogproject.controller.auth;

import lombok.RequiredArgsConstructor;
import org.example.blogproject.service.auth.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserRestController {
    private final UserService userService;

    //회원가입 - 아이디(유저이름) 중복 확인
    @GetMapping("/check-username")
    public ResponseEntity<Object> checkUsername(@RequestParam("username") String username) {
        boolean result = userService.checkUsername(username);
        return ResponseEntity.ok().body(result);
    }

    //회원가입 - 이메일 중복 확인
    @GetMapping("/check-email")
    public ResponseEntity<Object> checkEmail(@RequestParam("email") String email) {
        boolean result = userService.checkEmail(email);
        return ResponseEntity.ok().body(result);
    }
}
