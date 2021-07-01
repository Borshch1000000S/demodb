package com.borshch.demodb.service;

import com.borshch.demodb.model.Corsina;
import com.borshch.demodb.model.Customer;
import com.borshch.demodb.repository.CorsinaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service // сервисный бин - логика, отличается от @Component тем, что туда записывают логику, "истории"
@RequiredArgsConstructor
public class CorsinaService {

    private final CorsinaRepository corsinaRepository;

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