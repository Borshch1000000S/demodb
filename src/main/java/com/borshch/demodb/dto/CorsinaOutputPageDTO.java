package com.borshch.demodb.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "исходящая dto с page корзин")
public class CorsinaOutputPageDTO {

    @Schema(description = "всего страниц по запросу")
    private Integer numberOfPages;

    @Schema(description = "всего элементов по запросу")
    private Integer numberOfElements;

    @Schema(description = "текущий лист корзин")
    private List <CorsinaOutputDTO> listOfCorsinas;


}

