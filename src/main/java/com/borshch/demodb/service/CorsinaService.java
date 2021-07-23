package com.borshch.demodb.service;

import com.borshch.demodb.dto.CorsinaOutputDTO;
import com.borshch.demodb.model.Corsina;
import com.borshch.demodb.model.CorsinaGoods;
import com.borshch.demodb.model.Customer;
import com.borshch.demodb.model.Good;
import com.borshch.demodb.repository.CorsinaGoodsRepository;
import com.borshch.demodb.repository.CorsinaRepository;
import com.borshch.demodb.repository.GoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service // сервисный бин - логика, отличается от @Component тем, что туда записывают логику, "истории"
@RequiredArgsConstructor
public class CorsinaService {

    private final CorsinaRepository corsinaRepository;
    private final CustomerService customerService;
    private final GoodRepository goodRepository;
    private final CorsinaGoodsRepository corsinaGoodsRepository;

    /**
     * (общее описание метода) метод возвращает страницу адресов
     * (отделить энтером)
     *
     * @param limit  количество записей на странице
     * @param offset номер страницы
     * @return страница адресов
     */

    public Page<Corsina> getAll(Integer limit, Integer offset) {
        Pageable page = PageRequest.of(offset, limit);
        return corsinaRepository.findAll(page); //выдать пустой список
    }

    //JavaDoc используется только внутри Java

    public Corsina getByID(Integer id) {

        return corsinaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Corsina with id = " + id + " not found"));
    }

    public Corsina save(Corsina corsina) {   // просто сохранение, также сохранение без авторизации (авторизация только при оформлении заказа)

        Corsina savedCorsina = corsinaRepository.save(corsina); // внутри вызывается Validator // входящая перед вызовом метода
        System.out.println("savedCorsina = " + savedCorsina);
        return savedCorsina;
    }

    //дописать создания пустой корзины, в серсисе метод создания пустой корзины или каскад по созданию.

    @Transactional
    public Corsina discardCorsina(Integer id) { // по id корзины
        Corsina corsina = new Corsina();
        // по id корзины удалить все вложенные строки
        corsina.setIdCorsina(id);
        corsinaRepository.save(corsina);
        return corsina;
    }

    @Transactional
    public Corsina addNewRowToCorsina(Integer id, Good good, Integer numberOfGoods) {
        Corsina corsina = corsinaRepository.getOne(id);
        List <CorsinaGoods> listOfCorsinaGoods = corsina.getCorsinaGoods();

        CorsinaGoods corsinaGoods = new CorsinaGoods();

        corsinaGoods.setNumberOfGoods(numberOfGoods);
        corsinaGoods.setGood(good);

        listOfCorsinaGoods.add(corsinaGoods);
        corsina.setCorsinaGoods(listOfCorsinaGoods);

        corsinaGoodsRepository.save(corsinaGoods);
        return corsinaRepository.save(corsina);
    }

    @Transactional
    public Corsina removeRowFromCorsina(Integer id, Integer numberOfRowInCorsina) { // переделать по id товара
        Corsina corsina = corsinaRepository.getOne(id);
        List <CorsinaGoods> listOfCorsinaGoods = corsina.getCorsinaGoods();
        listOfCorsinaGoods.remove(numberOfRowInCorsina);
        return corsinaRepository.save(corsina);
    }

    @Transactional
    public Corsina changeNumberOfGoodsInRow (Integer id, Integer numberOfRowInCorsina, Integer numberOfGoods) { //переделать на вызов 1 приватного метода
        Corsina corsina = corsinaRepository.getOne(id);
        List <CorsinaGoods> listOfCorsinaGoods = corsina.getCorsinaGoods();
        CorsinaGoods corsinaGoods = listOfCorsinaGoods.get(numberOfRowInCorsina);
        corsinaGoods.setNumberOfGoods(numberOfGoods);

        corsinaGoodsRepository.save(corsinaGoods);
        return corsinaRepository.save(corsina);
    }

    @Transactional
    public Corsina incrementNumberOfGoodsInRow (Integer id, Integer numberOfRowInCorsina) {
        Corsina corsina = corsinaRepository.getOne(id);
        List <CorsinaGoods> listOfCorsinaGoods = corsina.getCorsinaGoods();
        CorsinaGoods corsinaGoods = listOfCorsinaGoods.get(numberOfRowInCorsina);
        corsinaGoods.setNumberOfGoods(corsinaGoods.getNumberOfGoods()+1);

        corsinaGoodsRepository.save(corsinaGoods);
        return corsinaRepository.save(corsina);
    }

    @Transactional
    public Corsina decrementNumberOfGoodsInRow (Integer id, Integer numberOfRowInCorsina) {
        Corsina corsina = corsinaRepository.getOne(id);
        List <CorsinaGoods> listOfCorsinaGoods = corsina.getCorsinaGoods();
        CorsinaGoods corsinaGoods = listOfCorsinaGoods.get(numberOfRowInCorsina);
        corsinaGoods.setNumberOfGoods(corsinaGoods.getNumberOfGoods()-1);
        if (corsinaGoods.getNumberOfGoods()==0) {corsinaGoodsRepository.delete(corsinaGoods);}
        else {corsinaGoodsRepository.save(corsinaGoods);}

        return corsinaRepository.save(corsina);
    }



    public void deleteById(Integer id) {
        corsinaRepository.deleteById(id);
    }

    @Transactional
    public Corsina put(Corsina corsina, Integer id) { // обновить
        corsina.setIdCorsina(id);
        corsinaRepository.save(corsina);
        return corsina;
    }


}