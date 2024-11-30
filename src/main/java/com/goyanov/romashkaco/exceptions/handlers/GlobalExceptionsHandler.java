package com.goyanov.romashkaco.exceptions.handlers;

import com.goyanov.romashkaco.exceptions.not.found.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionsHandler
{
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionsHandler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleProductNotFound(EntityNotFoundException ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> handleNoResourceFoundException(NoResourceFoundException ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Путь обращения указан неверно!");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleValidations(ConstraintViolationException ex)
    {
        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.get(0));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleOthers(Exception ex)
    {
        logger.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Непредвиденная ошибка на сервере!");
    }
}
