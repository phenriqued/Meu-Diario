package com.meuDiario.diary.infra.Exception.ErrorsHandlers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class ErrorHandlers {

    @ExceptionHandler(Exception.class)
    public String handlerError500(){
        return "error/500";
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public String handlerErro404() {
        return "error/404";
    }

}
