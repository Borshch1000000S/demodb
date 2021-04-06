package com.borshch.demodb.api;

import com.borshch.demodb.model.StorageGoods;
import com.borshch.demodb.repository.StorageGoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/v1/storageGoods")
@RequiredArgsConstructor

public class StorageGoodsApi {
    private final StorageGoodsRepository storageGoodsRepository;

    @GetMapping     //запрос get
    public List<StorageGoods> getAll(){
        return storageGoodsRepository.findAll();
    }

    @GetMapping("/{id}") //+id в пасвэрайбл
    public StorageGoods getOne(@PathVariable("id") Integer id) {
        return storageGoodsRepository.findById(id).orElseThrow(()->new EntityNotFoundException("StorageGoods with id = " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // чтобы 201 "создано"
    public StorageGoods save(@RequestBody StorageGoods storageGoods) {
        return storageGoodsRepository.save(storageGoods);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Integer id) {
        storageGoodsRepository.deleteById(id);
    }
}

