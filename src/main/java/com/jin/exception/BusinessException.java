package com.jin.exception;

/**
 * @author hujin
 * @version 2021/12/26
 */
public class BusinessException extends RuntimeException {

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }
}
