package com.borshch.demodb.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class StorageGoods {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Good good;



    @ManyToOne
    private Storage storage;

    //Количество товаров на складе, либо на складах

    private Integer numberOfGoodsRemained;

    //Решение для одного или нескольких складов
}
