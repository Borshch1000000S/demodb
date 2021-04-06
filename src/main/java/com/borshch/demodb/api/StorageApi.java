package com.borshch.demodb.api;


import com.borshch.demodb.model.Storage;
import com.borshch.demodb.repository.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/v1/storage")
@RequiredArgsConstructor

public class StorageApi {
    private final StorageRepository storageRepository;

    @GetMapping     //запрос get
    public List<Storage> getAll(){
        return storageRepository.findAll();
    }

    @GetMapping("/{id}") //+id в пасвэрайбл
    public Storage getOne(@PathVariable("id") Integer id) {
        return storageRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Storage with id = " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // чтобы 201 "создано"
    public Storage save(@RequestBody Storage storage) {
        return storageRepository.save(storage);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Integer id) {
        storageRepository.deleteById(id);
    }
}

