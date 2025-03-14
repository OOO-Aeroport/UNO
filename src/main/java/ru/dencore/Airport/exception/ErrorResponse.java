package ru.dencore.Airport.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    /**
     * Краткое сообщение об ошибке
     */
    private String message;

    /**
     * Подробное описание ошибки
     */
    private List<String> details;

}