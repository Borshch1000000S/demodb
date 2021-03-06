package com.borshch.demodb.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity

public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGood;


    @ManyToOne
    private Category category;

    private Double priceBuyed;
    private Double priceOfCellWithoutDiscount;


}
