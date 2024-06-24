package org.example.blogproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BlogErrorCode {
    USER_NOT_FOUND("해당 정보로 가입된 계정이 존재하지 않습니다."),
    PASSWORD_NOT_MATCH("비밀번호가 일치하지 않습니다."),
    INVALID_REQUEST("잘못된 요청입니다.");

    private final String message;
}
