package com.disney.mundo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> defaultErrorHandler(HttpServletRequest req, Exception e) {
        return new ResponseEntity<ErrorMessage>(new ErrorMessage("Error en tiempo de ejecucion", e.getMessage(), "1", req.getRequestURI()), HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> notFoundExceptionHandler(HttpServletRequest req,Exception e) {
        return new ResponseEntity<ErrorMessage>(new ErrorMessage("Not Found", e.getMessage(), "2", req.getRequestURI()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> validationExceptionHandler(HttpServletRequest req,Exception e) {
        return new ResponseEntity<ErrorMessage>(new ErrorMessage("Data Error ", e.getMessage(), "3", req.getRequestURI()), HttpStatus.NOT_ACCEPTABLE);
    }
}
