package com.borshch.demodb.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
public class CorsinaGoods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Corsina corsina; //

    @ManyToOne
    private Good good; //

    private Integer numberOfGoods;

}
