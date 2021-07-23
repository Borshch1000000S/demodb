package com.borshch.demodb.api;

import com.borshch.demodb.dto.CorsinaInputDTO;
import com.borshch.demodb.dto.CorsinaOutputDTO;
import com.borshch.demodb.dto.CorsinaOutputPageDTO;
import com.borshch.demodb.mapper.CorsinaDTOMapper;
import com.borshch.demodb.model.Corsina;
import com.borshch.demodb.model.CorsinaGoods;
import com.borshch.demodb.model.Customer;
import com.borshch.demodb.model.Good;
import com.borshch.demodb.service.CorsinaGoodsService;
import com.borshch.demodb.service.CorsinaService;
import com.borshch.demodb.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/corsina")
@RequiredArgsConstructor
public class CorsinaApi {


    private final CorsinaService corsinaService;
    private final CorsinaDTOMapper corsinaDTOMapper;
    private final CustomerService customerService;
    private final CorsinaGoodsService corsinaGoodsService;

    @Operation(summary = "получить страницу корзин")
    @GetMapping
    public CorsinaOutputPageDTO getAll(@Parameter(description = "колчество записей на странице")
                                       @RequestParam(required = false, defaultValue = "20") Integer limit,
                                       @Parameter(description = "ндекс страницы, начиная с 0 (для первой)")
                                       @RequestParam(required = false, defaultValue = "0") Integer offset) {

        Page<Corsina> listOfCorsinas = corsinaService.getAll(limit, offset);
        CorsinaOutputPageDTO corsinaOutputPageDTO = corsinaDTOMapper.convertToOutputPageDTO(listOfCorsinas);
        return corsinaOutputPageDTO;
    }

    @Operation(summary = "получить исходящий транспортный объект корзины по id")
    @GetMapping("/{id}")
    public CorsinaOutputDTO getOne(@PathVariable("id") Integer id) {

        return corsinaDTOMapper.convertToOutputDTO(corsinaService.getByID(id));
    }


    // ЛОГИКА:


    // 1) Вообще у клиента автоматически формируется пустая, если не было иной до регистрации, корзина в CustomerService, но нужно иметь возможность сбросить корзину
    //до нуля как отдельная опция
    @Operation(summary = "создание новой пустой корзины по переданному id корзины (сброс корзины)")
    @PutMapping("/{id}")
    public CorsinaOutputDTO discardCorsina(@PathVariable("id") Integer id) { //по id корзину
        return corsinaDTOMapper.convertToOutputDTO(corsinaService.discardCorsina(id));
            }

// 2) Добавить строку по id корзины, товару,

    @Operation(summary = "добавить строку товара с его количеством в корзину")
    @PutMapping("/{id}")
    public CorsinaOutputDTO addNewRowToCorsina(@PathVariable("id") Integer id, Good good, Integer numberOfGoods) { // на каждый случай пишем свою dto
        return corsinaDTOMapper.convertToOutputDTO(corsinaService.addNewRowToCorsina(id, good, numberOfGoods));
    }

// 3) Удалить строку товара и его количества из корзины

    @Operation(summary = "удалить строку товара и его количества из корзины")
    @PostMapping("/{id}")
    public CorsinaOutputDTO removeRowFromCorsina (@PathVariable("id") Integer id, Integer numberOfRowInCorsina) {
        return corsinaDTOMapper.convertToOutputDTO(corsinaService.removeRowFromCorsina(id, numberOfRowInCorsina));
    }

// Изменить количество товаров в корзине // это надо прописать в корзина/товар - вот там и реализуем, см. CorsinaGoodsApi

    @Operation(summary = "изменить количество товара в строке товара")
    @PutMapping("/{id}")
    public CorsinaOutputDTO changeNumberOfGoodsInRow (@PathVariable("id") Integer id, Integer numberOfRowInCorsina, Integer numberOfGoods) {
    return corsinaDTOMapper.convertToOutputDTO(corsinaService.changeNumberOfGoodsInRow(id, numberOfRowInCorsina, numberOfGoods));
    }

// Увеличить количество товаров в корзине // это надо прописать в корзина/товар - вот там и реализуем, см. CorsinaGoodsApi

    @Operation(summary = "увеличить на 1 количество товара в строке товара")
    @PutMapping("/{id}")
    public CorsinaOutputDTO incrementNumberOfGoodsInRow (@PathVariable("id") Integer id, Integer numberOfRowInCorsina) {
        return corsinaDTOMapper.convertToOutputDTO(corsinaService.incrementNumberOfGoodsInRow(id, numberOfRowInCorsina));
    }

    // Уменьшить количество товаров в корзине // это надо прописать в корзина/товар - вот там и реализуем, см. CorsinaGoodsApi

    @Operation(summary = "уменьшить на 1 количество товара в строке товара")
    @PutMapping("/{id}")
    public CorsinaOutputDTO decrementNumberOfGoodsInRow (@PathVariable("id") Integer id, Integer numberOfRowInCorsina) {
        return corsinaDTOMapper.convertToOutputDTO(corsinaService.decrementNumberOfGoodsInRow(id, numberOfRowInCorsina));
    }







// Получить корзину по id клиента
    @Operation(summary = "получить транспортный объект текущей корзины по id пользователя")
    @GetMapping("/{id}") // пользователя
    public CorsinaOutputDTO getCorsinaByCustomerId(@PathVariable ("id") Integer id) {
        Customer customer = customerService.getByID(id);
        return corsinaDTOMapper.convertToOutputDTO(customer.getCurrentCorsina());
    }

// Получить корзины, измененные с A даты до B даты (проверка, что A менеее B, а B менее .now) - на сладенькое, с Антоном
    //видимо, хитрым SQL-запросом в сервисе




    @Operation(summary = "создание объекта клиента в БД")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // чтобы 201 "создано"

    public CorsinaOutputDTO save(@RequestBody @Valid CorsinaInputDTO corsinaInputDTO) {

        Corsina corsina = corsinaDTOMapper.convertToEntity(corsinaInputDTO);

        return corsinaDTOMapper.convertToOutputDTO(corsinaService.save(corsina));
    }


    @Operation(summary = "удалить объект из базы данных по id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Integer id) {
        corsinaService.deleteById(id);
    }

    @Operation(summary = "перезаписать объект в БД по номеру id")
    @PutMapping("/{id}")
    public CorsinaOutputDTO update(@PathVariable("id") Integer id, @RequestBody CorsinaInputDTO corsinaInputDTO) {
        Corsina corsina = corsinaDTOMapper.convertToEntity(corsinaInputDTO);
        corsina.setIdCorsina(id);
        return corsinaDTOMapper.convertToOutputDTO(corsina);
    }


}



