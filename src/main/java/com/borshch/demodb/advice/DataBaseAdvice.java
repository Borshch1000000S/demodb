package com.borshch.demodb.advice;

import com.borshch.demodb.dto.ExceptionDto;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Order(0) //чем выше номер, тем он приоритетнее
@RestControllerAdvice
public class DataBaseAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND) // чтобы получить 404
    @ExceptionHandler(EntityNotFoundException.class) //в ней указывается тип Ex, которую перехватывает этот метод
    //.class - строго объекты этого класса
    public ExceptionDto handleEntityNotFoundEx(EntityNotFoundException e) {//
        return ExceptionDto.builder()          //это типа конструктора
                .message(e.getMessage())       //который по сути цепочка .setter - ов,
                .timeOfException(LocalDateTime.now())              //синтаксис - билдер вначале, далее назначение параметров
                .build();                                          //д.б. аннотация @Builder
    }
}
