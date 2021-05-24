package com.borshch.demodb.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Builder // объекты, которые строят другие объекты
public class ExceptionDto {
    private String message;
    private LocalDateTime timeOfException;
    private List <String> messages;


}
