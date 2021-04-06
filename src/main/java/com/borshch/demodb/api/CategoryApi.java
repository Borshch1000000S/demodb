package com.borshch.demodb.api;


import com.borshch.demodb.model.Category;
import com.borshch.demodb.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor

public class CategoryApi {
    private final CategoryRepository categoryRepository;

    @GetMapping     //запрос get
    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}") //+id в пасвэрайбл
    public Category getOne(@PathVariable("id") Integer id) {
        return categoryRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Category with id = " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // чтобы 201 "создано"
    public Category save(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Integer id) {
        categoryRepository.deleteById(id);
    }
}
