package com.borshch.demodb.api;


import com.borshch.demodb.model.Good;
import com.borshch.demodb.repository.GoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/v1/good")
@RequiredArgsConstructor

public class GoodApi {
    private final GoodRepository goodRepository;

    @GetMapping     //запрос get
    public List<Good> getAll(){
        return goodRepository.findAll();
    }

    @GetMapping("/{id}") //+id в пасвэрайбл
    public Good getOne(@PathVariable("id") Integer id) {
        return goodRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Good with id = " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // чтобы 201 "создано"
    public Good save(@RequestBody Good good) {
        return goodRepository.save(good);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Integer id) {
        goodRepository.deleteById(id);
    }
}
