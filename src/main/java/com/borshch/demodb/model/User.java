package com.borshch.demodb.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity //этот класс будет мапиться в БД на табл.
@Data // создает геттеры, сеттеры, иквалс, хэшкод, тустринг, конструкторы
public class User {
    @Id //javax - тип это ключ
    @GeneratedValue // БД сама генерирует ключ (Id)
    private Integer id;// только с объектами

    private String login;
    private String password;



}
