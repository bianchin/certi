package br.org.certi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ExceptionHandlerController {


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> error(MethodArgumentTypeMismatchException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Error.ErrorBuilder.anError().withMessage("Formato inválido.").build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> error(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Error.ErrorBuilder.anError().withMessage(e.getMessage()).build());
    }
}
