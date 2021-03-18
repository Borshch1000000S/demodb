package com.borshch.demodb.repository;

import com.borshch.demodb.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository // бины, работающие с БД, можно не ставить
public interface AddressRepository extends JpaRepository<Address, Integer> {//<таблица, тип id>
//реализацию всех методов CrudRepository делает реал. всех методов,
    //JPA
}
