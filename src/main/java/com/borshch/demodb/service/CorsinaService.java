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
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CorsinaService {

    private final CorsinaRepository corsinaRepository;
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
        log.info("get page of corsina with limit {} and offset {}", limit, offset);
        Pageable page = PageRequest.of(offset, limit);
        return corsinaRepository.findAll(page); //выдать пустой список
    }

    //JavaDoc используется только внутри Java

    public Corsina getByID(Integer id) {
        log.info("get corsina by id {}", id); // залогировали
        return corsinaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Corsina with id = " + id + " not found"));
    }

    public Corsina save(Corsina corsina) {   // просто сохранение, также сохранение без авторизации (авторизация только при оформлении заказа)

        log.info("save corsina {}", corsina.toString());

        Corsina savedCorsina = corsinaRepository.save(corsina); // внутри вызывается Validator // входящая перед вызовом метода

        Integer id = corsina.getIdCorsina();

        if (!corsinaRepository.existsById(id)) {throw new EntityNotFoundException("Corsina with id = " + id + " is not saved");}

        return savedCorsina;
    }

    //дописать создания пустой корзины, в серсисе метод создания пустой корзины или каскад по созданию.

    @Transactional
    public Corsina discardCorsina(Integer id) { // по id корзины
        log.info("discard corsina with id {}", id);
        if (!corsinaRepository.existsById(id)) {throw new EntityNotFoundException("Corsina with id = " + id + " is not saved");}
        else
        {Corsina corsina = new Corsina();

        // по id корзины удалить все вложенные строки

        corsina.setIdCorsina(id);
        corsinaRepository.save(corsina);

        log.debug("corsina saved");

        corsinaGoodsRepository.deleteByCorsinaIdCorsina(id); // это нормально, правильно?

        log.debug("corsina goods deleted");
        return corsina;}
    }

    @Transactional
    public Corsina addNewRowToCorsina(Integer idCorsina, Integer idGood, Integer numberOfGoods) {
        Corsina corsina = corsinaRepository.getOne(idCorsina);
        List <CorsinaGoods> listOfCorsinaGoods = corsina.getCorsinaGoods();
        CorsinaGoods corsinaGoods = new CorsinaGoods();
        corsinaGoods.setGood(goodRepository.getOne(idGood));
        corsinaGoods.setNumberOfGoods(numberOfGoods);

        corsinaGoodsRepository.save(corsinaGoods);

        listOfCorsinaGoods.add(corsinaGoods);

        corsina.setCorsinaGoods(listOfCorsinaGoods);

                return corsina;
    }

    @Transactional
    public Corsina removeRowFromCorsina(Integer idCorsina, Integer idGood) {
        Corsina corsina = corsinaRepository.getOne(idCorsina);
        List <CorsinaGoods> listOfCorsinaGoods = corsina.getCorsinaGoods();

        corsinaGoodsRepository.deleteByGoodIdGoodAndCorsinaIdCorsina(idGood, idCorsina);
        return corsinaRepository.save(corsina);
    }




    @Transactional
    public Corsina changeNumberOfGoodsInRow (Integer id, Integer idGood, Integer numberOfGoods) { //переделать на вызов 1 приватного метода
        Corsina corsina = corsinaRepository.getOne(id);
        List <CorsinaGoods> listOfCorsinaGoods = corsina.getCorsinaGoods();
        CorsinaGoods corsinaGoods = corsinaGoodsRepository.findByGood_IdGoodAndCorsinaIdCorsina(idGood, id);
        corsinaGoods.setNumberOfGoods(numberOfGoods);
        corsinaGoodsRepository.save(corsinaGoods);

        return corsinaRepository.save(corsina);
    }


    @Transactional
    public Corsina incrementNumberOfGoodsInRow (Integer id, Integer idGood) {
        Integer numberOfGoods = corsinaGoodsRepository.findByGood_IdGoodAndCorsinaIdCorsina(idGood, id).getNumberOfGoods();
        return changeNumberOfGoodsInRow(id, idGood, (numberOfGoods + 1));
    }

    @Transactional
    public Corsina decrementNumberOfGoodsInRow (Integer id, Integer idGood) {
        Corsina corsina = corsinaRepository.getOne(id);
        List <CorsinaGoods> listOfCorsinaGoods = corsina.getCorsinaGoods();

        CorsinaGoods corsinaGoods = corsinaGoodsRepository.findByGood_IdGoodAndCorsinaIdCorsina(idGood, id);

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