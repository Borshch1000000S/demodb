package com.borshch.demodb.api;


import com.borshch.demodb.model.Customer;
import com.borshch.demodb.repository.CustomerRepository;
import com.borshch.demodb.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor

public class CustomerApi {


    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    //private final

    @GetMapping     //запрос get
    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

    @GetMapping("/{id}") //+id в пасвэрайбл
    public Customer getOne(@PathVariable("id") Integer id) {
        return customerRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Customer with id = " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // чтобы 201 "создано"
    public Customer save(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Integer id) {
        customerRepository.deleteById(id);
    }
}

