package com.borshch.demodb.service;

import com.borshch.demodb.model.Address;
import com.borshch.demodb.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // сервисный бин - логика, отличается от @Component тем, что туда записывают логику, "истории"
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    /**
     * (общее описание метода) метод возвращает страницу адресов
     * (отделить энтером)
     *
     * @param limit  количество записей на странице
     * @param offset номер страницы
     * @return страница адресов
     */
    public Page<Address> getAll(Integer limit, Integer offset) {
        Pageable page = PageRequest.of(offset, limit);
        return addressRepository.findAll(page);
    }

    //JavaDoc используется только внутри Java

    public Optional<Address> getByID(Integer id) {
        return addressRepository.findById(id);
    }

    public Address save(Address address) {
        Address savedAddress = addressRepository.save(address);
        return savedAddress;
    }

    public void deleteById(Integer id) {
        addressRepository.deleteById(id);
    }

}


