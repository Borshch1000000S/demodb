package com.borshch.demodb.service;

import com.borshch.demodb.model.Corsina;
import com.borshch.demodb.model.CorsinaGoods;
import com.borshch.demodb.repository.CorsinaGoodsRepository;
import com.borshch.demodb.repository.CorsinaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class CorsinaGoodsService {

    private final CorsinaGoodsRepository corsinaGoodsRepository;

        public Page<CorsinaGoods> getAll(Integer limit, Integer offset) {
        Pageable page = PageRequest.of(offset, limit);
        return corsinaGoodsRepository.findAll(page);
    }

    public CorsinaGoods getByID(Integer id) {

        return corsinaGoodsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Corsina with id = " + id + " not found"));
    }

    public CorsinaGoods save(CorsinaGoods corsinaGoods) { //

        CorsinaGoods savedCorsinaGoods = corsinaGoodsRepository.save(corsinaGoods); // внутри вызывается Validator // входящая перед вызовом метода
        System.out.println("savedCorsina = " + savedCorsinaGoods);
        return savedCorsinaGoods;
    }

}






//
//
//
//
//
//    public void deleteById(Integer id) {
//        corsinaRepository.deleteById(id);
//    }
//
//}
