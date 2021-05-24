package com.borshch.demodb.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Schema(description = "исходящая DTO с информацией о адресе")
@Data
public class AddressOutputDTO { // создали dto, чтобы возвращать контролируемое количество данных - полей,
    // аутпут - то, что отдали пользователю, можно инпут

    @Schema(description = "ID объекта dto адреса")
    private Integer id;
    @Schema(description = "страна адреса")
    private String country;
    @Schema(description = "регион адреса")
    private String region;
    @Schema(description = "город адреса")
    private String city;
    @Schema(description = "улица адреса")
    private String street;
    @Schema(description = "почтовый индекс адреса")
    private Integer index;
    @Schema(description = "номер здания адреса")
    private String numberOfHouse;
    @Schema(description = "номер квартиры/офиса")
    private String numberOfAppartmnet;
}
