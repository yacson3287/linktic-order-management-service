package com.linktic.order_management_service.infrastructure.exceptions;

import com.linktic.order_management_service.domain.exceptions.BadRequestExceptionService;
import com.linktic.order_management_service.domain.exceptions.ExceptionDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handlerBadRequestExceptionController(MethodArgumentNotValidException exception) {
        ExceptionDetail exceptionDetail = new ExceptionDetail("Operation could not be executed");
        exception.getBindingResult().getAllErrors().forEach(error -> {
                    String key = ((FieldError) error).getField();
                    String value = error.getDefaultMessage();
                    exceptionDetail.addDetail(key, value);
                }
        );
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exceptionDetail.getJson());
    }

    @ExceptionHandler(value = {BadRequestExceptionService.class})
    public ProblemDetail handlerBadRequestException(BadRequestExceptionService e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }

}
