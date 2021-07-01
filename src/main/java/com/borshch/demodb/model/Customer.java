package com.borshch.demodb.model;
import com.borshch.demodb.constraint.Phone;
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

    //найти польз. БД по логину
    private String login;

    private String password;

    private String firstName;
    private String secondName;

@Phone(message = "телефон д.б. указан корректно в формате...")
    private String mobilePhone;
    private String email;




    private LocalDateTime birthDay;

    private LocalDateTime registrationDate;





    @JsonIgnoreProperties("customers")
    @ManyToOne
    private Manager personalManager;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Address> addresses;//связь с адресами

    @OneToOne(cascade = CascadeType.REMOVE)
    private Corsina currentCorsina;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

}
