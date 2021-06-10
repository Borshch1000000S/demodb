package com.borshch.demodb.dto;

import com.borshch.demodb.constraint.Phone;
import com.borshch.demodb.model.Address;
import com.borshch.demodb.model.Corsina;
import com.borshch.demodb.model.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "исходящая DTO с информацией о клиенте")
@Data
public class CustomerOutputDTO {


    @Schema(description = "ID объекта dto клиента")
    private Integer idCustomer;
    @Schema(description = "Логин клиента")
    private String login;
    // пароль же трём из аутпут, верно?
    @Schema(description = "имя")
    private String firstName;
    @Schema(description = "фамилия")
    private String secondName;
    @Schema(description = "номер мобильного телефона")
    private String mobilePhone;
    @Schema(description = "адрес электронной почты")
    private String email;
    @Schema(description = "день рождения")
    private LocalDateTime birthday;
    @Schema(description = "дата регистрации")
    private LocalDateTime registrationDate;
    @Schema(description = "лист адресов клиента")
    private List<Address> addresses;
    @Schema(description = "текущая корзина с заказами")
    private Corsina currentCorsina;
    @Schema(description = "лист ранее собранных заказов")
    private List<Order> orders;

}