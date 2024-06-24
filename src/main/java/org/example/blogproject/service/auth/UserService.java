package org.example.blogproject.service.auth;

import lombok.RequiredArgsConstructor;
import org.example.blogproject.domain.blog.Blog;
import org.example.blogproject.exception.BlogErrorCode;
import org.example.blogproject.domain.auth.Role;
import org.example.blogproject.domain.auth.User;
import org.example.blogproject.exception.BlogException;
import org.example.blogproject.repository.BlogRepository;
import org.example.blogproject.repository.RoleRepository;
import org.example.blogproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final BlogRepository blogRepository;

    //회원가입
    @Transactional
    public User signUp( User user) {
        //사용자 자동으로 권한주기 Default 역할 : 사용자
        Role userRole = roleRepository.findByName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);

        //회원가입시 자동으로 Blog 생성
        Blog blog = new Blog();
        blog.setName(user.getUsername());
        blog.setUser(user);

        blogRepository.save(blog);
        try {
            return user;
        } catch (Exception e) {
            throw new RuntimeException(BlogErrorCode.INVALID_REQUEST.getMessage(), e);
        }

    }

    //아이디 중복 확인
    @Transactional(readOnly = true)
    public boolean checkUsername(String username) {
        return userRepository.findByUsername(username) != null;
    }

    //이메일 중복 확인
    @Transactional(readOnly = true)
    public Boolean checkEmail(String email) {
        return userRepository.findByEmail(email) != null;
    }

    //로그인
    @Transactional(readOnly = true)
    public User logIn(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new BlogException(BlogErrorCode.USER_NOT_FOUND);
        if (!user.getPassword().equals(password))
            throw new BlogException(BlogErrorCode.PASSWORD_NOT_MATCH);
        return user;
    }

    //사용자 정보 조회
    @Transactional(readOnly = true)
    public User userInfoByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
