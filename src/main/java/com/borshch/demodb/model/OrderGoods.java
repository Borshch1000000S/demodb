package com.borshch.demodb.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class OrderGoods {

    @Id
    @GeneratedValue
    private Integer id;


    @ManyToOne
    private Order order; //

    @ManyToOne
    private Good good; //

    private Integer numberOfGoods;
}