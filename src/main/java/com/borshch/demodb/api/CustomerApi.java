package com.borshch.demodb.api;


import com.borshch.demodb.dto.CustomerInputDTO;
import com.borshch.demodb.dto.CustomerOutputDTO;
import com.borshch.demodb.dto.CustomerOutputPageDTO;
import com.borshch.demodb.mapper.CustomerDTOMapper;
import com.borshch.demodb.model.Address;
import com.borshch.demodb.model.Customer;
import com.borshch.demodb.repository.CustomerRepository;
import com.borshch.demodb.service.AddressService;
import com.borshch.demodb.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor

public class CustomerApi {


    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private final CustomerDTOMapper customerDTOMapper;
    private final AddressService addressService;


    @Operation(summary = "получить страницу клиентов")
    @GetMapping
    public CustomerOutputPageDTO getAll(@Parameter(description = "колчество записей на странице")
                                        @RequestParam(required = false, defaultValue = "20") Integer limit,
                                        @Parameter(description = "ндекс страницы, начиная с 0 (для первой)")
                                        @RequestParam(required = false, defaultValue = "0") Integer offset) {

        Page<Customer> listOfCustomers = customerService.getAll(limit, offset);
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


        //ДУМАЮ, ЭТО НЕ НАДО
//        List<Address> listOfAddresses = customer.getAddresses();
//
//        for (int i = 0; i < listOfAddresses.size(); i++) {
//            addressService.save(listOfAddresses.get(i));
//        } // сохранили вложенные адреса


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

        //УДАЛИТЬ ЛИ СТАРЫЕ СВЯЗАННЫЕ АДРЕСА, КАК МУСОР ПРИ АПДЕЙТЕ????? ИЛИ ОНО АВТОМАТИЧЕСКИ ОТРАБОТАЕТ КАК ВЛОЖЕННЫЕ СУЩНОСТИ???

//        CustomerOutputDTO oldCustomer = getOne(id);
//        List<Address> listOfOldAddresses = oldCustomer.getAddresses();
//
//        for (int i = 0; i < listOfOldAddresses.size(); i++) {
//            addressService.deleteById(listOfOldAddresses.get(i).getId());
//        }
//
//        List<Address> listOfAddresses = customer.getAddresses();
//
//        for (int i = 0; i < listOfAddresses.size(); i++) {
//            addressService.save(listOfAddresses.get(i));
//        } // сохранили вложенные адреса


        customer.setIdCustomer(id);
        return customerDTOMapper.convertToOutputDTO(customer);
    }

}

