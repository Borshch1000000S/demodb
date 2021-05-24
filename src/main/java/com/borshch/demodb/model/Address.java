package com.borshch.demodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //ctrl + пробел
    // это не сквозная, а своя последовательность id для адресов
    private Integer id;

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

    @JsonIgnoreProperties("addresses") // игнорировать json-атрибут поля для объекта класса
    @ManyToOne
    private Customer customer;

}
