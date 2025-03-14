package ru.dencore.Airport.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.dencore.Airport.exception.ErrorResponse;
import ru.dencore.Airport.exception.NotFoundException;

import java.util.Collections;
import java.util.List;


/**
 * Глобальный обработчик исключений
 */
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        ErrorResponse errorResponse = new ErrorResponse("Ошибка валидации", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {

        ErrorResponse errorResponse = new ErrorResponse("Ресурс не найден", Collections.singletonList(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

    }


}
