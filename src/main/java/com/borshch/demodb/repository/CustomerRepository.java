package com.borshch.demodb.repository;

import com.borshch.demodb.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query; // именно jpa!!!(бд)
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository <Customer, Integer> {

    Optional<Customer> getCustomerByLogin(String login); // парсит параметр после слова By, get также важен, акже важен возвращаемый объект
    //реализация спринговская

    // Учить SQL Postgres

    @Query(value = " SELECT c FROM Customer c WHERE (EXTRACT(YEAR, CURRENT_DATE) - EXTRACT(YEAR, c.birthDay)) > :age")     //для написания запроса SQL, здесь JPQL, ответ - кусок таблица
    List<Customer> getAllOlder (@Param("age") int age);     // @Param - списали с Sql запроса, задавая значение параметру запроса

    @Modifying // это значит, что мы сами делаем метод, изменяющий БД, на всё, кроме чтения, кроме встроенных CRUD методов
    void deleteByLogin (String login);

}
