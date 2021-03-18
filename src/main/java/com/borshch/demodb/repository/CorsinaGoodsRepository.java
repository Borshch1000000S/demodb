package com.borshch.demodb.repository;

import com.borshch.demodb.model.Corsina;
import com.borshch.demodb.model.CorsinaGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorsinaGoodsRepository extends JpaRepository<CorsinaGoods, Integer> {
}
