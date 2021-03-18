package com.borshch.demodb.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Storage {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToMany(mappedBy = "storage")
    private List<StorageGoods> storageGoods;

    @Enumerated(EnumType.STRING)
    private StorageNamesEnum nameOfStorage;

}
