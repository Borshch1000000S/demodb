package com.borshch.demodb.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "исходящая DTO с информацией о адресе в формате list + номер общий адресов + количество страниц + номер текущей страницы")
@Data

public class AddressOutputPageDTO {
    @Schema(description = "всего страниц по запросу")
    private Integer numberOfPages;

    @Schema(description = "всего элементов по запросу")
    private Long numberOfElements;

    @Schema(description = "текущий лист адресов")
    private List <AddressOutputDTO> listOfAddresses;
}
