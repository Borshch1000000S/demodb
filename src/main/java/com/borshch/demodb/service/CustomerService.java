package com.borshch.demodb.service;


import com.borshch.demodb.model.Address;
import com.borshch.demodb.model.Corsina;
import com.borshch.demodb.model.Customer;
import com.borshch.demodb.repository.AddressRepository;
import com.borshch.demodb.repository.CorsinaRepository;
import com.borshch.demodb.repository.CustomerRepository;
import com.borshch.demodb.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service // сервисный бин - логика, отличается от @Component тем, что туда записывают логику, "истории"
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CorsinaService corsinaService;

    @Transactional // так как возможно изменение общего списка клиентов при запросе GetAll
    public Page<Customer> getAll(Integer limit, Integer offset) {
        Pageable page = PageRequest.of(offset, limit);
        return customerRepository.findAll(page);
    }

    //JavaDoc используется только внутри Java

    // @Transactional не нужна, т.к. достаем только одного клиента по неповторимому id
    //если кинется Exception, фиг с ним, просто повторим запрос, слетит только один поток

    public Customer getByID(Integer id) {

        return customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer with id = " + id + " not found"));
    }

    public Customer getByLogin(String login) {

        return customerRepository.getCustomerByLogin(login).orElseThrow(() -> new EntityNotFoundException("Customer with login = " + login + " not found"));
    }

    @Transactional // т.к. сохраняется клиент и вложенная сущность - адрес
    public Customer save(Customer customer) {

        if (customer.getCurrentCorsina()==null) { // если пустая, то приделать сразу корзину, никогда не null
            // ни у одного customer-а
            Corsina corsina = new Corsina(); //empty
            corsinaService.save(corsina);
            customer.setCurrentCorsina(corsinaService.save(corsina));
        }


        Customer savedCustomer = customerRepository.save(customer); // внутри вызывается Validator // входящая перед вызовом метода+

        System.out.println("savedCustomer = " + savedCustomer);

        return savedCustomer;
    }

    @Transactional // т.к. сохраняется клиент и вложенные сущности (адрес); добавил возможность обновить по id
    public Customer update(Customer customer, Integer id) { // в терминологии сервиса это не Put, а Update
        customer.setIdCustomer(id);
        customerRepository.save(customer);

        return customer;
    }

    @Transactional //т.к. удаляется две сущности - клиент и адрес
    public void deleteById(Integer id) {
        customerRepository.deleteById(id);
    }

}
