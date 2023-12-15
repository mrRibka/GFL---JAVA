package com.example.busstation.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error_page");
        return modelAndView;
    }
}
