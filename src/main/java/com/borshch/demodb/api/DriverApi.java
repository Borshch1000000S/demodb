package com.borshch.demodb.api;


import com.borshch.demodb.model.Driver;
import com.borshch.demodb.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/v1/driver")
@RequiredArgsConstructor

public class DriverApi {
    private final DriverRepository driverRepository;

    @GetMapping     //запрос get
    public List<Driver> getAll(){
        return driverRepository.findAll();
    }

    @GetMapping("/{id}") //+id в пасвэрайбл
    public Driver getOne(@PathVariable("id") Integer id) {
        return driverRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Driver with id = " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // чтобы 201 "создано"
    public Driver save(@RequestBody Driver driver) {
        return driverRepository.save(driver);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Integer id) {
        driverRepository.deleteById(id);
    }
}

