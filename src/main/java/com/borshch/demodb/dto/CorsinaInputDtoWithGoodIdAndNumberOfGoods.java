package com.borshch.demodb.dto;

import com.borshch.demodb.model.CorsinaGoods;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CorsinaInputDtoWithGoodIdAndNumberOfGoods {

    @NotBlank
    private Integer idGood;
    @NotBlank
    private Integer numberOfGoods;
}



