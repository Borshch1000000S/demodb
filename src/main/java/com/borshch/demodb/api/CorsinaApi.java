package com.borshch.demodb.api;


import com.borshch.demodb.model.Corsina;
import com.borshch.demodb.repository.CorsinaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/v1/corsina")
@RequiredArgsConstructor

public class CorsinaApi {
    private final CorsinaRepository corsinaRepository;

    @GetMapping     //запрос get
    public List<Corsina> getAll(){
        return corsinaRepository.findAll();
    }

    @GetMapping("/{id}") //+id в пасвэрайбл
    public Corsina getOne(@PathVariable("id") Integer id) {
        return corsinaRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Corsina with id = " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // чтобы 201 "создано"
    public Corsina save(@RequestBody Corsina corsina) {
        return corsinaRepository.save(corsina);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Integer id) {
        corsinaRepository.deleteById(id);
    }
}
