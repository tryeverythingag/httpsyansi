package com.java.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description:统一异常处理器
 * @author ron
 * @data 2019/10/29- 21:55
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handException(Exception ex) {
        return "redirect:/pages/error/exception.jsp";
    }
}
