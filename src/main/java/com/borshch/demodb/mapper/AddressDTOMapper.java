package com.borshch.demodb.mapper;

import com.borshch.demodb.dto.AddressInputDTO;
import com.borshch.demodb.dto.AddressOutputDTO;
import com.borshch.demodb.model.Address;
import org.springframework.stereotype.Component;

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
}
