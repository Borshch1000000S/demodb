package com.borshch.demodb.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AddressOutputDTO { // создали dto, чтобы возвращать контролируемое количество данных - полей,
    // аутпут - то, что отдали пользователю, можно инпут

    private Integer id;
    private String country;
    private String region;
    private String city;
    private String street;
    private Integer index;
    private String numberOfHouse;
    private String numberOfAppartmnet;

}
