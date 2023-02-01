package ciamb.demo.springaxondemo.command.api.PL.controller;

import ciamb.demo.springaxondemo.core.api.exception.EmptyNameExcpetion;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.ExecutionException;

@RestControllerAdvice
@Log4j2
public class GlobalErrorController {

    @ExceptionHandler(EmptyNameExcpetion.class)
    public ResponseEntity<String> handleEmptyNameException(EmptyNameExcpetion emptyNameExcpetion) {
        log.error("Il nome non può essere vuoto!");
        return new ResponseEntity<>(emptyNameExcpetion.getErrorMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExecutionException.class)
    public ResponseEntity<String> handleExecutionException(ExecutionException executionException) {
        log.error("Il nome non può essere vuoto!");
        return new ResponseEntity<>(executionException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InterruptedException.class)
    public ResponseEntity<String> handleInterruptedException(InterruptedException interruptedException) {
        log.error("Il nome non può essere vuoto!");
        return new ResponseEntity<>(interruptedException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
