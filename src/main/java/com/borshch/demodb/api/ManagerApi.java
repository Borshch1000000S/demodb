package com.borshch.demodb.api;


import com.borshch.demodb.model.Manager;
import com.borshch.demodb.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/v1/manager")
@RequiredArgsConstructor

public class ManagerApi {
    private final ManagerRepository managerRepository;

    @GetMapping     //запрос get
    public List<Manager> getAll(){
        return managerRepository.findAll();
    }

    @GetMapping("/{id}") //+id в пасвэрайбл
    public Manager getOne(@PathVariable("id") Integer id) {
        return managerRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Manager with id = " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // чтобы 201 "создано"
    public Manager save(@RequestBody Manager manager) {
        return managerRepository.save(manager);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Integer id) {
        managerRepository.deleteById(id);
    }
}
