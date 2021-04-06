package com.borshch.demodb.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity

public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String login;
    private String password;

    private double monthEfficiencyInDollars;
    private double salary;
    private double promissedPrime;
    private double actualPrime;
    private LocalDateTime birthday;


    @OneToMany(mappedBy = "personalManager")
    private List<Customer> customers;

}
