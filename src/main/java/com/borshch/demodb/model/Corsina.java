package com.borshch.demodb.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data

public class Corsina {
    @Id
    @GeneratedValue
    private Integer idCorsina;
    private LocalDateTime dateOfCreation;
    private LocalDateTime dateOfLastChange;

    @OneToMany(mappedBy = "corsina")
    private List<CorsinaGoods> corsinaGoods; //


}