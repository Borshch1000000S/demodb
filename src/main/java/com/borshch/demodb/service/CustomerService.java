package com.borshch.demodb.service;


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
import javax.persistence.EntityNotFoundException;


@Service // сервисный бин - логика, отличается от @Component тем, что туда записывают логику, "истории"
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    //private final AddressRepository addressRepository;
    //private final AddressService addressService;

    //private final CorsinaRepository corsinaRepository;
    //private final CorsinaService corsinaService;

    //private final OrderRepository orderRepository;
    //private final OrderService orderService;


    // ВОПРОС: А КАК БЫТЬ СО ВЛОЖЕННЫМИ СВЯЗАННЫМИ СУЩНОСТЯМИ? ИХ В СЕРВИСЕ СОХРАНЯТЬ ИЛИ В АПИ? ИЛИ ОНИ АВТОМАТИЧЕСКИ СОХРАНЯТСЯ?
    // ВОПРОС: СОХРАНЕНИЕ ВЛОЖЕННОГО АДРЕСА, КОРЗИНЫ, ЗАКАЗОВ КЛИЕНТА ЯВЛЯЕТСЯ ТРАНЗАКЦИЕЙ?


    public Page<Customer> getAll(Integer limit, Integer offset) {
        Pageable page = PageRequest.of(offset, limit);
        return customerRepository.findAll(page);
    }

    //JavaDoc используется только внутри Java

    public Customer getByID(Integer id) {

        return customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer with id = " + id + " not found"));
    }

    public Customer save(Customer customer) {

        Customer savedCustomer = customerRepository.save(customer); // внутри вызывается Validator // входящая перед вызовом метода
        System.out.println("savedCustomer = " + savedCustomer);


        return savedCustomer;
    }

    public void deleteById(Integer id) {
        customerRepository.deleteById(id);
    }

}
