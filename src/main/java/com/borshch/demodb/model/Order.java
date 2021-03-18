package com.borshch.demodb.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Orders") //переименовали
@Data

public class Order {
    @Id
    @GeneratedValue
    private Integer idOrder;
    private LocalDateTime dateOfPay;
    private LocalDateTime dateOfDelivery;
    private Double priceOfDelivery;
    private Double discount;

    @ManyToOne
    private Customer customer;

    @Enumerated (EnumType.STRING) // для ЕНАМА
    private OrderStatusEnum orderStatus; //

    @Enumerated (EnumType.STRING) // для ЕНАМА
    private AvailibilityEnum availibilityStatus;

    @OneToOne
    private Address address;

    @ManyToOne
    private Driver driver;

    @OneToMany(mappedBy = "order")
    private List <OrderGoods> orderGoods;
}
