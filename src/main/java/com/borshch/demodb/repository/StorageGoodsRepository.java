package com.borshch.demodb.repository;

import com.borshch.demodb.model.StorageGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageGoodsRepository extends JpaRepository<StorageGoods, Integer> {
}
