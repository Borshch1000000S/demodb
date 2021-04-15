package com.borshch.demodb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //этот класс будет мапиться в БД на табл.
@Data // создает геттеры, сеттеры, иквалс, хэшкод, тустринг, конструкторы
public class User {
    @Id //javax - тип это ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY) // БД сама генерирует ключ (Id)
    private Integer id;// только с объектами

    private String login;
    @JsonIgnore // никому никогда не отдавать
    private String password;



}
