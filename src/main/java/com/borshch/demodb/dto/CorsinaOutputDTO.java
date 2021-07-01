package com.borshch.demodb.dto;

import com.borshch.demodb.model.CorsinaGoods;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "исходящая DTO с информацией о корзине")
@Data
    public class CorsinaOutputDTO {

        @Schema(description = "id корзины")
        Integer id;

        @Schema(description = "дата создания корзины")
        private LocalDateTime dateOfCreation;

        @Schema(description = "дата последнего изменения в корзине")
        private LocalDateTime dateOfLastChange;

        @Schema(description = "лист строк товаров с указанием их количества")
        private List<CorsinaGoods> corsinaGoods; // вполне можеть быть пустой

    }


