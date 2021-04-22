package com.borshch.demodb.service;

import com.borshch.demodb.model.Address;
import com.borshch.demodb.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // сервисный бин - логика, отличается от @Component тем, что туда записывают логику, "истории"
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public List<Address> getAll () {
        return addressRepository.findAll();
    }

    public Optional<Address> getByID(Integer id) {
        return addressRepository.findById(id);
    }

    public Address save(Address address) {
        Address savedAddress = addressRepository.save(address);
        return savedAddress;
    }

    public void deleteById (Integer id) {
        addressRepository.deleteById(id);
    }

}


