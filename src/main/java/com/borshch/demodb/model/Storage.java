package com.borshch.demodb.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "storage", cascade = CascadeType.REMOVE)
    private List<StorageGoods> storageGoods;

    @Enumerated(EnumType.STRING)
    private StorageNamesEnum nameOfStorage;

}
