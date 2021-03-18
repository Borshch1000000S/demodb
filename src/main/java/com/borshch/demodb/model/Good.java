package com.borshch.demodb.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity

public class Good {

    @Id
    @GeneratedValue
    private Integer idGood;


    @ManyToOne
    private Category category;

    private Double priceBuyed;
    private Double priceOfCellWithoutDiscount;

    @OneToMany(mappedBy = "good")//
    private List<CorsinaGoods> corsinaGoods;//

    @OneToMany(mappedBy = "good")//
    private List<OrderGoods> listOforderGoods;//

    @OneToMany(mappedBy = "good")
    private List<StorageGoods> storageGoods;

}
