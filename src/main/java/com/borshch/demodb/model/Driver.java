package com.borshch.demodb.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data

public class Driver {
    @Id
    @GeneratedValue
    private Integer IdDriver;
    private String firstName;
    private String secondName;
    private String driverLicense;
    private String mobilePhone;
    private String email;
    private String car;

    @OneToMany(mappedBy = "driver")
    List<Order> orders;
}
