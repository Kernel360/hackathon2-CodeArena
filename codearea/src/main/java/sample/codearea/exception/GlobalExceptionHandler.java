package sample.codearea.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity notifiedException() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

//    @ExceptionHandler({Exception.class})
//    public ResponseEntity exception() {
//        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
//    }

}
