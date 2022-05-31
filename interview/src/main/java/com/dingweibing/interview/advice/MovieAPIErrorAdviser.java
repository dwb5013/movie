package com.dingweibing.interview.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class MovieAPIErrorAdviser {
    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<Object> exceptionHandler(ConstraintViolationException e, ServletWebRequest request) {
        Map<String, String> objectObjectHashMap = new LinkedHashMap<>();
        objectObjectHashMap.put("timestamp", new Date().toString());
        objectObjectHashMap.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        objectObjectHashMap.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        objectObjectHashMap.put("message", e.getMessage());
        objectObjectHashMap.put("path", request.getRequest().getRequestURI());
        return new ResponseEntity<>(objectObjectHashMap, HttpStatus.BAD_REQUEST);
    }
}
