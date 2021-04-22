package com.borshch.demodb.dto;

import lombok.Data;

@Data
public class AddressInputDTO {

    private String country;
    private String region;
    private String city;
    private String street;
    private Integer index;
    private String numberOfHouse;
    private String numberOfAppartmnet;
}
