package com.borshch.demodb.api;


import com.borshch.demodb.model.OrderGoods;
import com.borshch.demodb.repository.OrderGoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/v1/orderGoods")
@RequiredArgsConstructor

public class OrderGoodsApi {
    private final OrderGoodsRepository orderGoodsRepository;

    @GetMapping     //запрос get
    public List<OrderGoods> getAll(){
        return orderGoodsRepository.findAll();
    }

    @GetMapping("/{id}") //+id в пасвэрайбл
    public OrderGoods getOne(@PathVariable("id") Integer id) {
        return orderGoodsRepository.findById(id).orElseThrow(()->new EntityNotFoundException("OrderGoods with id = " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // чтобы 201 "создано"
    public OrderGoods save(@RequestBody OrderGoods orderGoods) {
        return orderGoodsRepository.save(orderGoods);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Integer id) {
        orderGoodsRepository.deleteById(id);
    }
}

