package com.borshch.demodb.mapper;


import com.borshch.demodb.dto.CorsinaInputDTO;
import com.borshch.demodb.dto.CorsinaOutputDTO;
import com.borshch.demodb.dto.CorsinaOutputPageDTO;
import com.borshch.demodb.dto.CustomerInputDTO;
import com.borshch.demodb.model.Corsina;
import com.borshch.demodb.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CorsinaDTOMapper {

    public Corsina convertToEntity(CorsinaInputDTO corsinaInputDTO) {
        Corsina corsina = new Corsina();

        corsina.setCorsinaGoods(corsinaInputDTO.getCorsinaGoods());
        corsina.setDateOfCreation(corsinaInputDTO.getDateOfCreation());
        corsina.setDateOfLastChange(corsinaInputDTO.getDateOfLastChange());

        return corsina;
    }

    public CorsinaOutputDTO convertToOutputDTO (Corsina corsina) {
        CorsinaOutputDTO corsinaOutputDTO = new CorsinaOutputDTO();

        corsinaOutputDTO.setId(corsina.getIdCorsina());
        corsinaOutputDTO.setCorsinaGoods(corsina.getCorsinaGoods());
        corsinaOutputDTO.setDateOfCreation(corsina.getDateOfCreation());
        corsinaOutputDTO.setDateOfLastChange(corsina.getDateOfLastChange());

        return corsinaOutputDTO;
    }

    public CorsinaOutputPageDTO convertToOutputPageDTO (Page<Corsina> pageOfCorsinas) {
        CorsinaOutputPageDTO corsinaOutputPageDTO = new CorsinaOutputPageDTO();
        corsinaOutputPageDTO.setNumberOfPages(pageOfCorsinas.getTotalPages());
        corsinaOutputPageDTO.setNumberOfElements((int) pageOfCorsinas.getTotalElements());
        corsinaOutputPageDTO.setListOfCorsinas(pageOfCorsinas.toList().stream().map(this::convertToOutputDTO).collect(Collectors.toList()));

        return corsinaOutputPageDTO;
    }

}
