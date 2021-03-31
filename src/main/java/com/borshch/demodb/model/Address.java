package com.borshch.demodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //ctrl + пробел
    // это не сквозная, а своя последовательность id для адресов
    private Integer id;

    private String country;
    private String region;
    private String city;
    private String street;
    private Integer index;
    private String numberOfHouse;
    private String numberOfAppartmnet;

    @JsonIgnoreProperties("addresses") // игнорировать json-атрибут поля для объекта класса
    @ManyToOne

    private Customer customer;

}
