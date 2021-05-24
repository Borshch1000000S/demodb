package com.borshch.demodb.advice;

import com.borshch.demodb.dto.ExceptionDto;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice //включает Component //для RestController-а, нужен для передачи Ошибок ФронтЕнду)
@Order(-1)
//RESTCONTRADVICE ТОЛЬКО ДЛЯ API
public class CommonAdvice extends ResponseEntityExceptionHandler {//диспатчер сам вызовет Advice, в Api не пиши!!!

    //если метод разместить в саму Api, то сработает только для этого Api, но с аннотациями!!!!!+
    //есть в RestControllerAdvice assignableTypes, для кого эта Адвайс будет применима

//    @ResponseStatus(HttpStatus.NOT_FOUND) // чтобы получить 404
//    @ExceptionHandler(EntityNotFoundException.class) //в ней указывается тип Ex, которую перехватывает этот метод
//    //.class - строго объекты этого класса
//    public ExceptionDto handleEntityNotFoundEx(EntityNotFoundException e) {//
//        return ExceptionDto.builder()          //это типа конструктора
//                .message(e.getMessage())       //который по сути цепочка .setter - ов,
//                .timeOfException(LocalDateTime.now())              //синтаксис - билдер вначале, далее назначение параметров
//                .build();                                          //д.б. аннотация @Builder
//    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // чтобы получить 404
    @ExceptionHandler(Exception.class) //в ней указывается тип Ex, которую перехватывает этот метод
    //.class - строго объекты этого класса
    public ExceptionDto handleEx(Exception e) {//
        return ExceptionDto.builder()          //это типа конструктора
                .message(e.getMessage())       //который по сути цепочка .setter - ов,
                .timeOfException(LocalDateTime.now())              //синтаксис - билдер вначале, далее назначение параметров
                .build();                                          //д.б. аннотация @Builder
    }
    //COMPITI PERCASA сделать валидацию handler-ом для валидации входящей dto


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @Override
    //этот метод Override, поэтому ему не нужно указание, с кем работать @ExceptionHandler

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<String>();

        for(FieldError error : ex.getBindingResult().getFieldErrors()) {     //вот здесь код требует разъяснения, вообще непонятно !!!!!
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        } // Что это за типы ошибок??? Почему разбивка на FieldError и ObjectError???

        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(ExceptionDto.builder()
                .timeOfException(LocalDateTime.now())
                .messages(errors)
                .build());
    }


    //





}
