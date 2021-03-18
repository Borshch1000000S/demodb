package com.borshch.demodb.model;

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

    @ManyToOne
    private Customer customer;

}
