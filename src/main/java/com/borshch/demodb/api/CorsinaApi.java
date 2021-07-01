package com.borshch.demodb.api;

import com.borshch.demodb.dto.CorsinaInputDTO;
import com.borshch.demodb.dto.CorsinaOutputDTO;
import com.borshch.demodb.dto.CorsinaOutputPageDTO;
import com.borshch.demodb.mapper.CorsinaDTOMapper;
import com.borshch.demodb.model.Corsina;
import com.borshch.demodb.model.CorsinaGoods;
import com.borshch.demodb.model.Customer;
import com.borshch.demodb.repository.CorsinaRepository;
import com.borshch.demodb.repository.CustomerRepository;
import com.borshch.demodb.service.CorsinaGoodsService;
import com.borshch.demodb.service.CorsinaService;
import com.borshch.demodb.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Operation(summary = "создание новой пустой корзины клиента по переданному id клиента / сброс корзины")
    @PutMapping("/{id}")
    public CorsinaOutputDTO createEmptyCorsina(@PathVariable("id") Integer id) { //по id Customer-а

        Corsina corsina = new Corsina(); //пустая
        Customer customer = customerService.getByID(id);
        corsina.setDateOfLastChange(LocalDateTime.now()); // тип изменили время изменения
        customer.setCurrentCorsina(corsina);

        customerService.put(customer, id); // перезаписали, правильно же, что id не слетит при перезаписи?
        return corsinaDTOMapper.convertToOutputDTO(corsina);
    }

// 2) Добавить строку товар/корзина в корзину

    @Operation(summary = "добавить строку CORSINAGOODS в корзину")
    @PutMapping("/{id}")  // по id корзины, ведь у корзины тоже есть Repository
    public CorsinaOutputDTO addNewRowToCorsina(@PathVariable("id") Integer id, CorsinaGoods corsinaGoods) {
        Corsina corsina = corsinaService.getByID(id);

        if (corsina.getCorsinaGoods().isEmpty()) {
            List<CorsinaGoods> currentListOfRows = new ArrayList<CorsinaGoods>();
            currentListOfRows.add(corsinaGoods);
            corsina.setCorsinaGoods(currentListOfRows);
        } else {
            corsina.getCorsinaGoods().add(corsinaGoods);
        }
        corsina.setDateOfLastChange(LocalDateTime.now());
        corsinaService.put(corsina, id);
        return corsinaDTOMapper.convertToOutputDTO(corsina);

    }

// 3) Удалить товар (строку CORSINAGOODS) из корзины

    @Operation(summary = "удалить строку CORSINAGOODS из корзины")
    @PostMapping("/{id}") // по id корзины, ведь у корзины тоже есть Repository
    public CorsinaOutputDTO removeRowFromCorsina(@PathVariable("id") Integer id, CorsinaGoods corsinaGoods) {
        Corsina corsina = corsinaService.getByID(id);
        List<CorsinaGoods> currentListOfRows = corsina.getCorsinaGoods();

// на пустой и несозданный не проверяем, ибо если пришёл запрос на удаление
// товара из корзины, значит и корзина есть, и товар есть, в противном случае косяк фронтенд
// в остальных случаях пусть падает поток

                corsina.getCorsinaGoods().remove(corsinaGoods);
                corsina.setDateOfLastChange(LocalDateTime.now());
                corsinaService.put(corsina, id);
        return corsinaDTOMapper.convertToOutputDTO(corsina);
    }



// Изменить количество товаров в корзине // это надо прописать в корзина/товар - вот там и реализуем, см. CorsinaGoodsApi,
    // не забыть дописать дату изменения корзины


// Получить корзину по id клиента
    @Operation(summary = "получить транспортный объект текущей корзины по id пользователя")
    @GetMapping("/{id}") // пользователя
    public CorsinaOutputDTO getCorsinaOfCustomerByCustomerId(@PathVariable ("id") Integer id) {
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



