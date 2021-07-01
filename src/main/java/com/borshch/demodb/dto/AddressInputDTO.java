package com.borshch.demodb.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Data
public class AddressInputDTO {


    @NotBlank(message = "поле country должно содержать хотя бы один значимый символ") // проверка на черный символ, Java Validation Constraints
    private String country;

    @NotBlank(message = "поле region должно содержать хотя бы один значимый символ")
    private String region;
    @NotBlank(message = "поле city должно содержать хотя бы один значимый символ")
    private String city;
    @NotBlank(message = "поле street должно содержать хотя бы один значимый символ, в случае отсутствия заполняется текстом none")
    private String street;

    @Max(999_999)
    private Integer index;
    @NotBlank(message = "поле numberOfHouse должно содержать хотя бы один значимый символ")
    private String numberOfHouse;
    @NotBlank(message = "поле numberOfAppartment должно содержать хотя бы один значимый символ")
    private String numberOfAppartmnet;

}
