package com.borshch.demodb.api;

import com.borshch.demodb.model.User;
import com.borshch.demodb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor

public class UserApi {
    private final UserRepository userRepository;

    @GetMapping     //запрос get
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}") //+id в пасвэрайбл
    public User getOne(@PathVariable("id") Integer id) {
        return userRepository.findById(id).orElseThrow(()->new EntityNotFoundException("User with id = " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // чтобы 201 "создано"
    public User save(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Integer id) {
        userRepository.deleteById(id);
    }
}

