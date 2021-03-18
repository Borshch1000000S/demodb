package com.borshch.demodb.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
public class CorsinaGoods {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Corsina corsina; //

    @ManyToOne
    private Good good; //

    private Integer numberOfGoods;

}
