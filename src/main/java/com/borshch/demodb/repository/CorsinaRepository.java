package com.borshch.demodb.repository;

import com.borshch.demodb.model.Corsina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorsinaRepository extends JpaRepository<Corsina, Integer> {
}
