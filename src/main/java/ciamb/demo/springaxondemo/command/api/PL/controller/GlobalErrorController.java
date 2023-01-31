package ciamb.demo.springaxondemo.command.api.PL.controller;

import ciamb.demo.springaxondemo.core.api.exception.EmptyNameExcpetion;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class GlobalErrorController {

    @ExceptionHandler(EmptyNameExcpetion.class)
    public ResponseEntity<String> handleEmptyNameException(EmptyNameExcpetion emptyNameExcpetion) {
        log.error("Il nome non può essere vuoto!");
        return new ResponseEntity<>(emptyNameExcpetion.getErrorMessage(), HttpStatus.BAD_REQUEST);
    }

}
