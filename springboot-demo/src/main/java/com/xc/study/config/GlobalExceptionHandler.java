package com.xc.study.config;

import com.xc.study.contants.CustomException;
import com.xc.study.contants.ResOrExceEnum;
import com.xc.study.entity.GlobalResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public GlobalResponse<String> handMethodArgumentNotValidException(CustomException exception) {
        return new GlobalResponse<>(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public GlobalResponse<String> handMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ObjectError objectError = exception.getBindingResult().getAllErrors().get(0);
        GlobalResponse<String> response = new GlobalResponse<>(ResOrExceEnum.ARGS_ERROR);
        if (!StringUtils.isEmpty(objectError.getDefaultMessage())) {
            response.setMsg(objectError.getDefaultMessage());
        }
        return response;
    }
}
