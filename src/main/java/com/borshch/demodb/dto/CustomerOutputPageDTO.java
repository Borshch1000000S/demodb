package com.borshch.demodb.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "исходящая DTO с информацией о клиентах в формате list + количетсво общее адресов" +
        " + количество страниц + номер текущей страницы")
@Data

public class CustomerOutputPageDTO {
    @Schema(description = "всего страниц по запросу")
    private Integer numberOfPages;

    @Schema(description = "всего элементов по запросу")
    private long numberOfElements;
    @Schema(description = "текущий лист клиентов")
    private List<CustomerOutputDTO> listOfCustomers;

}
