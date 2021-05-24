package com.borshch.demodb.service;

import com.borshch.demodb.model.Address;
import com.borshch.demodb.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
        return addressRepository.findAll(page); //выдать пустой список
    }

    //JavaDoc используется только внутри Java

    public Address getByID(Integer id) {

        return addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Address with id = " + id + " not found"));
    }

    public Address save(Address address) {
        
        Address savedAddress = addressRepository.save(address); // внутри вызывается Validator // входящая перед вызовом метода
        System.out.println("savedAddress = " + savedAddress);
        return savedAddress;
    }

    public void deleteById(Integer id) {
        addressRepository.deleteById(id);
    }

}


