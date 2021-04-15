package com.borshch.demodb.api;

import com.borshch.demodb.dto.AddressInputDTO;
import com.borshch.demodb.dto.AddressOutputDTO;
import com.borshch.demodb.model.Address;
import com.borshch.demodb.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
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

//??? Может, написать метод по копированию полей из сущности в разные ДТО и наоборот?????
// Или воспользоваться библиотеками, а то тупо получается



    @GetMapping     //если запрос Гет, то в ответе сработает вот этот метод
    public List<AddressOutputDTO> getAll() {
        List<Address> listofAllAddresses = addressRepository.findAll();
        List<AddressOutputDTO> listOfOutputDTO = new ArrayList<AddressOutputDTO>(); // Можно в АПИ пользоваться арээйлистом?

        for (int i = 0; i < listofAllAddresses.size(); i++) {
            AddressOutputDTO dtoi = new AddressOutputDTO();
            dtoi.setId(listofAllAddresses.get(i).getId());
            dtoi.setCountry(listofAllAddresses.get(i).getCountry());
            dtoi.setRegion(listofAllAddresses.get(i).getRegion());
            dtoi.setCity(listofAllAddresses.get(i).getCity());
            dtoi.setStreet(listofAllAddresses.get(i).getStreet());
            dtoi.setIndex(listofAllAddresses.get(i).getIndex());
            dtoi.setNumberOfHouse(listofAllAddresses.get(i).getNumberOfHouse());
            dtoi.setNumberOfAppartmnet(listofAllAddresses.get(i).getNumberOfAppartmnet());
            listOfOutputDTO.add(dtoi);
        }

        return listOfOutputDTO;

    }





    @GetMapping("/{id}")
    //поддиректория от 17 строки - {id} - это пассварайбл, здесь мы сказали, что назовем ID поддиректорию запроса
    public AddressOutputDTO getOne(@PathVariable("id") Integer id) { // а здесь ее сделали аргументом

        Address address = addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Address with id = " + id + " not found"));
        AddressOutputDTO dtoi = new AddressOutputDTO();

        dtoi.setId(address.getId());
        dtoi.setCountry(address.getCountry());
        dtoi.setRegion(address.getRegion());
        dtoi.setCity(address.getCity());
        dtoi.setStreet(address.getStreet());
        dtoi.setIndex(address.getIndex());
        dtoi.setNumberOfHouse(address.getNumberOfHouse());
        dtoi.setNumberOfAppartmnet(address.getNumberOfAppartmnet());

        return dtoi;
    }

    //ГЕТ, ГЕТПОАЙДИ, СОХАРНИТЬ, ОБНОВИТЬ, УДАЛИТЬ ДЛЯ СТАНДАРТНОГО API
    //гет НЕ Д. ИМЕТЬ ТЕЛА ЗАПРОСА

    // запилить везде dto
    // Есть библиотеки, но имена полей надо не менять !!!

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // чтобы 201 "создано"
    public AddressOutputDTO save(@RequestBody AddressInputDTO addressInputDTO) {
        Address address = new Address();

        address.setCountry(addressInputDTO.getCountry());
        address.setRegion(addressInputDTO.getRegion());
        address.setCity(addressInputDTO.getCity());
        address.setStreet(addressInputDTO.getStreet());
        address.setIndex(addressInputDTO.getIndex());
        address.setNumberOfHouse(addressInputDTO.getNumberOfHouse());
        address.setNumberOfAppartmnet(addressInputDTO.getNumberOfAppartmnet());

               addressRepository.save(address);

               AddressOutputDTO addressOutputDTO = new AddressOutputDTO();

               addressOutputDTO.setId(address.getId());
               addressOutputDTO.setCountry(address.getCountry());
        addressOutputDTO.setRegion(address.getRegion());
        addressOutputDTO.setCity(address.getCity());
        addressOutputDTO.setStreet(address.getStreet());
        addressOutputDTO.setIndex(address.getIndex());
        addressOutputDTO.setNumberOfHouse(address.getNumberOfHouse());
        addressOutputDTO.setNumberOfAppartmnet(address.getNumberOfAppartmnet());

        return addressOutputDTO;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Integer id) {
        addressRepository.deleteById(id);
    }





    @PutMapping("/{id}")
    public AddressOutputDTO update(@PathVariable("id") Integer id, @RequestBody AddressInputDTO addressInputDTO) {

        Address address = new Address();
        AddressOutputDTO addressOutputDTO = new AddressOutputDTO();

        address.setId(id);
        address.setCountry(addressInputDTO.getCountry());
        address.setRegion(addressInputDTO.getRegion());
        address.setCity(addressInputDTO.getCity());
        address.setStreet(addressInputDTO.getStreet());
        address.setIndex(addressInputDTO.getIndex());
        address.setNumberOfHouse(addressInputDTO.getNumberOfHouse());
        address.setNumberOfAppartmnet(addressInputDTO.getNumberOfAppartmnet());

        addressRepository.save(address);
        //создали дто, закопировали только его поля

        addressOutputDTO.setId(address.getId());
        addressOutputDTO.setCountry(address.getCountry());
        addressOutputDTO.setRegion(address.getRegion());
        addressOutputDTO.setCity(address.getCity());
        addressOutputDTO.setStreet(address.getStreet());
        addressOutputDTO.setIndex(address.getIndex());
        addressOutputDTO.setNumberOfHouse(address.getNumberOfHouse());
        addressOutputDTO.setNumberOfAppartmnet(address.getNumberOfAppartmnet());

        return addressOutputDTO;
    }


}


// почитать про Json, вопрос: почему именно Json для RestApi
// query param почему мы используем pathvariable, а не queryparams !!!!!!!!!!!!!!!!!!!
//перевести всю фигню на dto ))))).

//https://www.baeldung.com/     stackoverflow
//офиц документация спринга

//MVC - model(rep), view (интерфейс, общение с пользователем, то есть у нас Api, RESTController), c (controller - то, что сервис)
