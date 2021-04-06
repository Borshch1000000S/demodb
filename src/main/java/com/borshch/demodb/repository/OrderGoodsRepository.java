package com.borshch.demodb.repository;

import com.borshch.demodb.model.OrderGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderGoodsRepository extends JpaRepository<OrderGoods,Integer> {
}
