package com.borshch.demodb.api;


import com.borshch.demodb.dto.CustomerInputDTO;
import com.borshch.demodb.dto.CustomerOutputDTO;
import com.borshch.demodb.dto.CustomerOutputPageDTO;
import com.borshch.demodb.mapper.CustomerDTOMapper;
import com.borshch.demodb.model.Customer;
import com.borshch.demodb.repository.CustomerRepository;
import com.borshch.demodb.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;


@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor

public class CustomerApi {


    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private final CustomerDTOMapper customerDTOMapper;

    @Operation(summary = "получить страницу клиентов")
    @GetMapping
    public CustomerOutputPageDTO getAll(@Parameter(description = "колчество записей на странице")
                                        @RequestParam(required = false, defaultValue = "20") Integer limit,
                                        @Parameter(description = "ндекс страницы, начиная с 0 (для первой)")
                                        @RequestParam(required = false, defaultValue = "0") Integer offset) {

        Page <Customer> listOfCustomers = customerService.getAll(limit, offset);
        CustomerOutputPageDTO customerOutputPageDTO = customerDTOMapper.convertToOutPutPageDTO(listOfCustomers);
        return customerOutputPageDTO;
    }

    @Operation(summary = "получить исходящий транспортный объект клиента по id")
    @GetMapping("/{id}")
    public CustomerOutputDTO getOne(@PathVariable("id") Integer id) {

        return customerDTOMapper.convertToOutputDTO(customerService.getByID(id));
    }


    @Operation(summary = "создание объекта клиента в БД")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // чтобы 201 "создано"

    public CustomerOutputDTO save(@RequestBody @Valid CustomerInputDTO customerInputDTO) {

        Customer customer = customerDTOMapper.convertToEntity(customerInputDTO);
        return customerDTOMapper.convertToOutputDTO(customerService.save(customer));
    }


    @Operation(summary = "удалить объект из базы данных по id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Integer id) {
        customerService.deleteById(id);
    }

    @Operation(summary = "перезаписать объект в БД по номеру id")
    @PutMapping("/{id}")
    public CustomerOutputDTO update(@PathVariable("id") Integer id, @RequestBody CustomerInputDTO customerInputDTO) {
        Customer customer = customerDTOMapper.convertToEntity(customerInputDTO);
        customer.setIdCustomer(id);
        return customerDTOMapper.convertToOutputDTO(customer);
    }

}

