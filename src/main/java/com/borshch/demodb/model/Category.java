package com.borshch.demodb.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data

public class Category {
    @Id
    @GeneratedValue
    private Integer id;

    private String category;

    @OneToMany(mappedBy = "category")
    private List<Good> good;


}
