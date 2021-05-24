package com.borshch.demodb.mapper;

import com.borshch.demodb.dto.AddressInputDTO;
import com.borshch.demodb.dto.AddressOutputDTO;
import com.borshch.demodb.dto.AddressOutputPageDTO;
import com.borshch.demodb.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component

public class AddressDTOMapper {

    public Address convertToEntity (AddressInputDTO addressInputDTO) {

        Address address = new Address();

        address.setCountry(addressInputDTO.getCountry());
        address.setRegion(addressInputDTO.getRegion());
        address.setCity(addressInputDTO.getCity());
        address.setStreet(addressInputDTO.getStreet());
        address.setIndex(addressInputDTO.getIndex());
        address.setNumberOfHouse(addressInputDTO.getNumberOfHouse());
        address.setNumberOfAppartmnet(addressInputDTO.getNumberOfAppartmnet());

        return address;
    }

    public AddressOutputDTO convertToOutputDTO (Address address) {
        AddressOutputDTO addressOutputDTO = new AddressOutputDTO();

        addressOutputDTO.setIndex(address.getIndex());
        addressOutputDTO.setRegion(address.getRegion());
        addressOutputDTO.setCity(address.getCity());
        addressOutputDTO.setStreet(address.getStreet());
        addressOutputDTO.setIndex(address.getIndex());
        addressOutputDTO.setNumberOfHouse(address.getNumberOfHouse());
        addressOutputDTO.setNumberOfAppartmnet(address.getNumberOfAppartmnet());

        return addressOutputDTO;
    }

    public AddressOutputPageDTO convertToOutPutPageDTO (Page <Address> pageOfAddresses) {
        AddressOutputPageDTO addressOutputPageDTO = new AddressOutputPageDTO();
        addressOutputPageDTO.setListOfAddresses(pageOfAddresses.toList().stream().map(a-> convertToOutputDTO(a)).collect(Collectors.toList()));
        addressOutputPageDTO.setNumberOfPages(pageOfAddresses.getTotalPages());
        addressOutputPageDTO.setNumberOfElements(pageOfAddresses.getTotalElements());
        return addressOutputPageDTO;
    }

}
