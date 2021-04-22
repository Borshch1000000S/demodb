package com.borshch.demodb.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class AddressOutputPageDTO {
    private Integer numberOfPages;
    private Long numberOfElements;

    private List <AddressOutputDTO> listOfAddresses;
}
