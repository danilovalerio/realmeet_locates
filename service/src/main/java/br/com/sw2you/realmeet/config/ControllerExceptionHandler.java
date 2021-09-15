package br.com.sw2you.realmeet.config;

import br.com.sw2you.realmeet.api.model.ResponseError;
import br.com.sw2you.realmeet.exception.RoomNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Controller responsável por capturar os erros que ocorrem na aplicação
 * Por isso annotation RestControllerAdvice
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> onException(Exception exception) {
        System.out.println("Exception: " + exception);
        if (((MethodArgumentTypeMismatchException) exception).getCause().toString().contains("NumberFormatException")) {
            return exceptionGeneric(exception);
        }
        return exceptionGeneric(exception);
    }

    public ResponseEntity<Object> exceptionGeneric(Exception exception) {
        return new ResponseEntity<>(
            new ResponseError()
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .code(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage()),
            HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(Exception exception) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, exception);
    }

    private ResponseEntity<Object> buildResponseEntity(HttpStatus httpStatus, Exception exception) {
        return new ResponseEntity<>(
            new ResponseError()
                .status(HttpStatus.ACCEPTED.getReasonPhrase())
                .code(httpStatus.value())
                .message(exception.getMessage()),
            httpStatus
        );
    }
}
