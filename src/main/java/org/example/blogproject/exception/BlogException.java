package org.example.blogproject.exception;

import lombok.Getter;

@Getter
public class BlogException extends RuntimeException {

    private final String errorCode;

    public BlogException(BlogErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode.name();
    }

    public BlogException(BlogErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode.name();
    }
}
