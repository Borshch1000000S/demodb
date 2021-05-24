package com.borshch.demodb.api;

import com.borshch.demodb.dto.AddressInputDTO;
import com.borshch.demodb.dto.AddressOutputDTO;
import com.borshch.demodb.dto.AddressOutputPageDTO;
import com.borshch.demodb.mapper.AddressDTOMapper;
import com.borshch.demodb.model.Address;
import com.borshch.demodb.repository.AddressRepository;
import com.borshch.demodb.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController //бины, которые принимают http запросы к приложению - это рестконтроллер
// просто контроллер - устаревшая аннотация, серверный рендеринг и т.д., именно РЕСТ говорит о том, что все входящие
//и  выходящие в формате JSON

// все ответные объекты преобразуются в JSON строки
@RequestMapping("/api/v1/addresses") //этот контроллер будет обслужить /api/v1/addresses http запросы,
// те запросы, которые сюда прилетят вот в эту директорию
//адреса - это те, которые класс Address во мн. ч.
@RequiredArgsConstructor
public class AddressApi {


    private final AddressRepository addressRepository;
    private final AddressService addressService;
    private final AddressDTOMapper addressDTOMapper;


//??? Может, написать метод по копированию полей из сущности в разные ДТО и наоборот?????
// Или воспользоваться библиотеками, а то тупо получается


    //Swagger (OpenApi) - формат документации RESTApi, актуальна 3 версия.
    //https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-ui/1.5.7


    @Operation(summary = "получить страницу адресов") // для документации Swagger, аннотация Operation для методов,
    //Parameter для qwery param-ов
    //для классов и для полей классов Schema (тело запроса или параметры тела запроса)
    @GetMapping     //если запрос Гет, то в ответе сработает вот этот метод
    public AddressOutputPageDTO getAll(@Parameter(description = "количество записей на странице")
                                         @RequestParam(required = false, defaultValue = "20") Integer limit,
                                         @Parameter(description = "индекс страницы, начиная с 0 (для первой)")
                                         @RequestParam(required = false, defaultValue = "0") Integer offset) {

        System.out.println("limit = " + limit);
        System.out.println("offset = " + offset);
        // limit  - колво элементов, offset - смещение
        //по страницам поиска
        Page<Address> listofAllAddresses = addressService.getAll(limit, offset);

        // Можно в АПИ пользоваться арээйлистом? Можно!

        AddressOutputPageDTO addressOutputPageDTO = addressDTOMapper.convertToOutPutPageDTO(listofAllAddresses);



        return addressOutputPageDTO;
    }


    @Operation(summary = "получить транспортный объект адреса по номеру id")
    @GetMapping("/{id}")
    //поддиректория от 17 строки - {id} - это пассварайбл, здесь мы сказали, что назовем ID поддиректорию запроса

    public AddressOutputDTO getOne(@PathVariable("id") Integer id) { // а здесь ее сделали аргументом
        Address address = addressService.getByID(id);
        return addressDTOMapper.convertToOutputDTO(address);
    }


    //ГЕТ, ГЕТПОАЙДИ, СОХАРНИТЬ, ОБНОВИТЬ, УДАЛИТЬ ДЛЯ СТАНДАРТНОГО API
    //гет НЕ Д. ИМЕТЬ ТЕЛА ЗАПРОСА

    // запилить везде dto
    // Есть библиотеки, но имена полей надо не менять !!!

    @Operation(summary = "создание объекта адреса в БД")
    @PostMapping // пост - запостить, создать новую запись
    @ResponseStatus(HttpStatus.CREATED) // чтобы 201 "создано"

    public AddressOutputDTO save(@RequestBody @Valid AddressInputDTO addressInputDTO) { //@Valid здесь вызывается проверка
        // входящая дто валидируется перед вызовом метода

        Address address = addressDTOMapper.convertToEntity(addressInputDTO);
        addressService.save(address); // переписать через сервис
        AddressOutputDTO addressOutputDTO = addressDTOMapper.convertToOutputDTO(address);
        return addressOutputDTO;
    }

    @Operation(summary = "удалить объект из БД по номеру id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Integer id) {
        addressService.deleteById(id);
    }


    @Operation(summary = "перезаписать объект в БД по номеру id")
    @PutMapping("/{id}")
    public AddressOutputDTO update(@PathVariable("id") Integer id, @RequestBody AddressInputDTO addressInputDTO) {

        Address address = addressDTOMapper.convertToEntity(addressInputDTO);
        address.setId(id);

        addressService.save(address);
        //сохранили через сервис в репозиторий

        AddressOutputDTO addressOutputDTO = addressDTOMapper.convertToOutputDTO(address);
        return addressOutputDTO;
    }

}


// почитать про Json, вопрос: почему именно Json для RestApi
// query param почему мы используем pathvariable, а не queryparams !!!!!!!!!!!!!!!!!!!
//перевести всю фигню на dto ))))).

//https://www.baeldung.com/     stackoverflow
//офиц документация спринга

//MVC - model(rep), view (интерфейс, общение с пользователем, то есть у нас Api, RESTController), c (controller - то, что сервис)

//HW 22.04.2021 - переделать исходящее dto из getAll запроса, чтобы в нем содержалась информация
// о общем количестве страниц и общим количеством элементов


//



// ПОЧИТАТЬ ПРО АННОТАЦИИ, АННОТАЦИИ ДЛЯ АДРЕСА И ЕГО ДТО
// КАК ВЫЗВАТЬ ВАЛИАДАТОРА САМОСТОЯТЕЛЬНО?
