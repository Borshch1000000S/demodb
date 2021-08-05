package com.borshch.demodb.repository;

import com.borshch.demodb.model.Corsina;
import com.borshch.demodb.model.Customer;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CorsinaRepository extends JpaRepository<Corsina, Integer> {


}
