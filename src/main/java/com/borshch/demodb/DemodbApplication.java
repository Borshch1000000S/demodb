package com.borshch.demodb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j // добавить объект, который будет заниматься логгированием, его делает ломбок
@SpringBootApplication
public class DemodbApplication {


    public static void main(String[] args) {
        SpringApplication.run(DemodbApplication.class, args);
    }

}
