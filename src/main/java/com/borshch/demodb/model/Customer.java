package com.borshch.demodb.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCustomer;

    private String login;
    private String password;

    private String firstName;
    private String secondName;

    private String mobilePhone;
    private String email;




    private LocalDateTime birthDay;
    private LocalDateTime registrationDate;
    private LocalDateTime lastVisitDate;

    private Integer numberOfVisitsFRorLastMonth;




    @JsonIgnoreProperties("customers")
    @ManyToOne
    private Manager personalManager;

    @OneToMany(mappedBy = "customer")
    private List<Address> addresses;//связь с адресами

    @OneToOne
    private Corsina currentCorsina;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

}
