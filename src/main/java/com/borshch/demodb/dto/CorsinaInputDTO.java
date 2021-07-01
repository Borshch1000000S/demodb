package com.borshch.demodb.dto;

import com.borshch.demodb.model.CorsinaGoods;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CorsinaInputDTO {

        @NotBlank(message = "дата создания корзины не должна быть пустой") // как проверить на соотв. формата LocalDateTime???
    private LocalDateTime dateOfCreation;

        @NotBlank(message = "дата последнего измнения не должна быть пустой")
    private LocalDateTime dateOfLastChange;

    private List<CorsinaGoods> corsinaGoods; // вполне можеть быть пустой

}
