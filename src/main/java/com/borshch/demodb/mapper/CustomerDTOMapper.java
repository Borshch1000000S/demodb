package com.borshch.demodb.mapper;

import com.borshch.demodb.dto.*;
import com.borshch.demodb.model.Corsina;
import com.borshch.demodb.model.Customer;
import com.borshch.demodb.model.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import com.borshch.demodb.dto.CustomerInputDTO;
import com.borshch.demodb.dto.CustomerOutputDTO;
import com.borshch.demodb.dto.CustomerOutputPageDTO;
import com.borshch.demodb.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

    @Component
    public class CustomerDTOMapper {

        public Customer convertToEntity(CustomerInputDTO customerInputDTO) {
            Customer customer = new Customer();

            customer.setLogin(customerInputDTO.getLogin());
            customer.setPassword(customerInputDTO.getPassword());
            customer.setFirstName(customerInputDTO.getFirstName());
            customer.setSecondName(customerInputDTO.getSecondName());
            customer.setMobilePhone(customerInputDTO.getMobilePhone());
            customer.setEmail(customerInputDTO.getEmail());
            customer.setRegistrationDate(customerInputDTO.getRegistrationDate());
            customer.setAddresses(customerInputDTO.getAddresses());

            customer.setBirthDay(customerInputDTO.getBirthday()); // Без try, т.к. может быть null

            return customer;
        }

        public CustomerOutputDTO convertToOutputDTO(Customer customer) {

            CustomerOutputDTO customerOutputDTO = new CustomerOutputDTO();


            customerOutputDTO.setIdCustomer(customer.getIdCustomer());
            customerOutputDTO.setLogin(customer.getLogin());
            customerOutputDTO.setFirstName(customer.getFirstName());
            customerOutputDTO.setSecondName(customer.getSecondName());
            customerOutputDTO.setMobilePhone(customer.getMobilePhone());
            customerOutputDTO.setEmail(customer.getEmail());
            customerOutputDTO.setBirthday(customer.getBirthDay());
            customerOutputDTO.setRegistrationDate(customer.getRegistrationDate());
            customerOutputDTO.setAddresses(customer.getAddresses());
            customerOutputDTO.setCurrentCorsina(customer.getCurrentCorsina());
            customerOutputDTO.setOrders(customer.getOrders());

            return customerOutputDTO;
        }

        public CustomerOutputPageDTO convertToOutPutPageDTO(Page<Customer> pageOfCustomers) {
            CustomerOutputPageDTO customerOutputPageDTO = new CustomerOutputPageDTO();
            customerOutputPageDTO.setListOfCustomers(pageOfCustomers.toList().stream().map(a -> convertToOutputDTO(a)).collect(Collectors.toList()));
            customerOutputPageDTO.setNumberOfPages(pageOfCustomers.getTotalPages());
            customerOutputPageDTO.setNumberOfElements(pageOfCustomers.getTotalElements());
            return customerOutputPageDTO;
        }

    }
