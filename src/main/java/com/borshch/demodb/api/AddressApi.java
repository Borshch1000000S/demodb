package com.borshch.demodb.api;

import com.borshch.demodb.dto.AddressInputDTO;
import com.borshch.demodb.dto.AddressOutputDTO;
import com.borshch.demodb.mapper.AddressDTOMapper;
import com.borshch.demodb.model.Address;
import com.borshch.demodb.repository.AddressRepository;
import com.borshch.demodb.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

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


    @GetMapping     //если запрос Гет, то в ответе сработает вот этот метод
    public List<AddressOutputDTO> getAll(@RequestParam(required = false, defaultValue = "20") Integer limit,
                                         @RequestParam(required = false, defaultValue = "0") Integer offset) {

        System.out.println("limit = " + limit);
        System.out.println("offset = " + offset);
        // limit  - колво элементов, offset - смещение
        //по страницам поиска

        List<Address> listofAllAddresses = addressService.getAll();
        List<AddressOutputDTO> listOfOutputDTO = new ArrayList<AddressOutputDTO>(); // Можно в АПИ пользоваться арээйлистом? Можно!

        for (int i = 0; i < listofAllAddresses.size(); i++) {

            AddressOutputDTO dtoi = new AddressOutputDTO();
            dtoi = addressDTOMapper.convertToOutputDTO(listofAllAddresses.get(i));

            listOfOutputDTO.add(dtoi);
        }
        return listOfOutputDTO;

    }


    @GetMapping("/{id}")
    //поддиректория от 17 строки - {id} - это пассварайбл, здесь мы сказали, что назовем ID поддиректорию запроса
    public AddressOutputDTO getOne(@PathVariable("id") Integer id) { // а здесь ее сделали аргументом

        Address address = addressService.getByID(id).orElseThrow(() -> new EntityNotFoundException("Address with id = " + id + " not found"));

        AddressOutputDTO dto = new AddressOutputDTO();
        dto = addressDTOMapper.convertToOutputDTO(address);

        return dto;
    }


    //ГЕТ, ГЕТПОАЙДИ, СОХАРНИТЬ, ОБНОВИТЬ, УДАЛИТЬ ДЛЯ СТАНДАРТНОГО API
    //гет НЕ Д. ИМЕТЬ ТЕЛА ЗАПРОСА

    // запилить везде dto
    // Есть библиотеки, но имена полей надо не менять !!!


    @PostMapping // пост - запостить, создать новую запись
    @ResponseStatus(HttpStatus.CREATED) // чтобы 201 "создано"

    public AddressOutputDTO save(@RequestBody AddressInputDTO addressInputDTO) {

        Address address = new Address();
        address = addressDTOMapper.convertToEntity(addressInputDTO);

        addressService.save(address); // переписать через сервис

        AddressOutputDTO addressOutputDTO = new AddressOutputDTO();
        addressOutputDTO = addressDTOMapper.convertToOutputDTO(address);

        return addressOutputDTO;
    }



    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Integer id) {
        addressService.deleteById(id);
    }


    @PutMapping("/{id}")
    public AddressOutputDTO update(@PathVariable("id") Integer id, @RequestBody AddressInputDTO addressInputDTO) {

        Address address = new Address();
        AddressOutputDTO addressOutputDTO = new AddressOutputDTO();

        address = addressDTOMapper.convertToEntity(addressInputDTO);
        address.setId(id);

        addressService.save(address);
        //сохранили через сервис в репозиторий

        addressOutputDTO = addressDTOMapper.convertToOutputDTO(address);

        return addressOutputDTO;
    }


}


// почитать про Json, вопрос: почему именно Json для RestApi
// query param почему мы используем pathvariable, а не queryparams !!!!!!!!!!!!!!!!!!!
//перевести всю фигню на dto ))))).

//https://www.baeldung.com/     stackoverflow
//офиц документация спринга

//MVC - model(rep), view (интерфейс, общение с пользователем, то есть у нас Api, RESTController), c (controller - то, что сервис)
