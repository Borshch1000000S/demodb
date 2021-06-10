package com.borshch.demodb.dto;

import com.borshch.demodb.constraint.Phone;
import com.borshch.demodb.model.Address;
import com.borshch.demodb.model.Corsina;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CustomerInputDTO {
    @NotBlank(message = "логин должен содержать не менее 1 значимого символа")
    private String login;
    @NotBlank(message = "пароль не может быть пустым, рекомендуем не менее 8 символов")
    private  String password;
    @NotBlank(message = "имя не может быть пустым")
    private String firstName;
    @NotBlank(message = "фамилия не может быть пустой")
    private String secondName;
    @Phone() // уже есть сообщение в PhoneValidator-е
    private String mobilePhone;
    @Email(message = "e-mail должен быть указан корректно")
    private String email;

    //необязательные поля для заполнения
    private LocalDateTime birthday;
    private LocalDateTime registrationDate = LocalDateTime.now(); // надо взять текущую дату
    private List<Address> addresses; // ссылается на constraints AddressInputDTO

    //наверное, корзину и заказы не стоит передавать сразу, ведь когда пользователь только зареган,
    //корзины и заказов пока нет, корзина появляется либо при нажатии на кнопку корзина, либо при заказе товара,
    //а вот в CustomerOutputDTO уже и корзина и заказы и манагер.

}

