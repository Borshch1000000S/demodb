package com.borshch.demodb.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "исходящая DTO с информацией о адресе")
@Data
public class AddressOutputDTO { // создали dto, чтобы возвращать контролируемое количество данных - полей,
    // аутпут - то, что отдали пользователю, можно инпут

    @Schema(description = "ID объекта dto адреса")
    private Integer id;
    @Schema(description = "страна адреса")
    private String country;
    private String region;
    private String city;
    private String street;
    private Integer index;
    private String numberOfHouse;
    private String numberOfAppartmnet;
}
