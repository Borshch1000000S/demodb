package com.borshch.demodb.api;


import com.borshch.demodb.model.CorsinaGoods;
import com.borshch.demodb.repository.CorsinaGoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/v1/corsinaGoods")
@RequiredArgsConstructor

public class CorsinaGoodsApi {
    private final CorsinaGoodsRepository corsinaGoodsRepository;

    @GetMapping     //запрос get
    public List<CorsinaGoods> getAll(){
        return corsinaGoodsRepository.findAll();
    }

    @GetMapping("/{id}") //+id в пасвэрайбл
    public CorsinaGoods getOne(@PathVariable("id") Integer id) {
        return corsinaGoodsRepository.findById(id).orElseThrow(()->new EntityNotFoundException("CorsinaGoods with id = " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // чтобы 201 "создано"
    public CorsinaGoods save(@RequestBody CorsinaGoods corsinaGoods) {
        return corsinaGoodsRepository.save(corsinaGoods);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Integer id) {
        corsinaGoodsRepository.deleteById(id);
    }
}

