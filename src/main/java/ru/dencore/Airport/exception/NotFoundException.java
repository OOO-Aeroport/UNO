package ru.dencore.Airport.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение возникает, когда ресурс не найден
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }

}
