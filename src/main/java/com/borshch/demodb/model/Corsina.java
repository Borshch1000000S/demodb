package com.borshch.demodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data

public class Corsina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCorsina;
    private LocalDateTime dateOfCreation;
    private LocalDateTime dateOfLastChange;


    @JsonIgnoreProperties("corsina") //закрываем поле корзина у объектов корзинагудс
    //DTO data transfer object - объект состава трансфера данных, это класс, повторяющий сам класс - модель,
    //но с другим набором полей, сам dto - объект преобразуется в объект оригинального entity

    @OneToMany(mappedBy = "corsina")
    private List<CorsinaGoods> corsinaGoods; //


}