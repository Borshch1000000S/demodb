package com.borshch.demodb.api;

import com.borshch.demodb.model.Address;
import com.borshch.demodb.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController //бины, которые принимают http запросы к приложению - это рестконтроллер
// просто контроллер - устаревшая аннотация, серверный рендеринг и т.д.

// все ответные объекты преобразуются в JSON строки
@RequestMapping("/api/v1/addresses") //этот контроллер будет обслужить /api/v1/addresses http запросы,
// те запросы, которые сюда прилетят вот в эту директорию
//адреса - это те, которые класс Address во мн. ч.
@RequiredArgsConstructor
public class AddressApi {

    private final AddressRepository addressRepository;

    @GetMapping     //если запрос Гет, то в ответе сработает вот этот метод
    public List<Address> getAll(){
        return addressRepository.findAll();
    }

    @GetMapping("/{id}") //поддиректория от 17 строки - {id} - это пассварайбл, здесь мы сказали, что назовем ID поддиректорию запроса
    public Address getOne(@PathVariable("id") Integer id) { // а здесь ее сделали аргументом
        return addressRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Address with id = " + id + " not found"));
    }

    //ГЕТ, ГЕТПОАЙДИ, СОХАРНИТЬ, ОБНОВИТЬ, УДАЛИТЬ ДЛЯ СТАНДАРТНОГО API
    //гет НЕ Д. ИМЕТЬ ТЕЛА ЗАПРОСА

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // чтобы 201 "создано"
    public Address save(@RequestBody Address address) {
        return addressRepository.save(address);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Integer id) {
        addressRepository.deleteById(id);
    }


}



// почитать про Json, вопрос: почему именно Json для RestApi
// query param почему мы используем pathvariable, а не queryparams

//https://www.baeldung.com/     stackoverflow
//офиц документация спринга
