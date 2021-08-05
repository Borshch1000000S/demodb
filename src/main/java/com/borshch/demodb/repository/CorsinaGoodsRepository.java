package com.borshch.demodb.repository;

import com.borshch.demodb.model.Corsina;
import com.borshch.demodb.model.CorsinaGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorsinaGoodsRepository extends JpaRepository<CorsinaGoods, Integer> {

    public CorsinaGoods findByGood_IdGoodAndCorsinaIdCorsina(Integer goodIdGood, Integer corsinaIdCorsina); // так работает?
    public void deleteByGoodIdGoodAndCorsinaIdCorsina(Integer goodIdGood,Integer corsinaIdCorsina); // так работает?
    public void deleteByCorsinaIdCorsina (Integer corsinaIdCorsina);

}
