package com.borshch.demodb.api;


import com.borshch.demodb.model.Order;
import com.borshch.demodb.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor

public class OrderApi {
    private final OrderRepository orderRepository;

    @GetMapping     //запрос get
    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    @GetMapping("/{id}") //+id в пасвэрайбл
    public Order getOne(@PathVariable("id") Integer id) {
        return orderRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Order with id = " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // чтобы 201 "создано"
    public Order save(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Integer id) {
        orderRepository.deleteById(id);
    }
}

