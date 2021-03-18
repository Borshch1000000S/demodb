package com.borshch.demodb.config;

import com.borshch.demodb.model.Address;
import com.borshch.demodb.model.Customer;
import com.borshch.demodb.repository.AddressRepository;
import com.borshch.demodb.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor // аннотация создаст конструктор для наших автовайрд полей
public class DBFiller {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;
    // у него соя реализация от Спринга, которая подставится автоматически



    @PostConstruct
    private void fill() {

        Customer c1 = new Customer();
        c1.setLogin("user1");
        customerRepository.save(c1); // перед ссылками друг на друга сохранить!!!

        Customer c2 = new Customer();
        c2.setIdCustomer(1);

        Address a1 = new Address();
        a1.setCity("Москва");
        a1.setCountry("РФ");

        a1.setCustomer(c2); // тому, кто many, чтоб без листа

        addressRepository.save(a1);
    }

}
