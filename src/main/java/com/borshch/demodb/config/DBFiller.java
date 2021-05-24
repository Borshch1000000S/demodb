package com.borshch.demodb.config;

import com.borshch.demodb.model.*;
import com.borshch.demodb.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;


@Component
@RequiredArgsConstructor // аннотация создаст конструктор для наших автовайрд полей
public class DBFiller {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;
    private  final CategoryRepository categoryRepository;
    private final CorsinaRepository corsinaRepository;
    private final CorsinaGoodsRepository corsinaGoodsRepository;
    private final DriverRepository driverRepository;
    private final GoodRepository goodRepository;
    private final ManagerRepository managerRepository;
    private final OrderRepository orderRepository;
    private final OrderGoodsRepository orderGoodsRepository;
    private final StorageGoodsRepository storageGoodsRepository;
    private final StorageRepository storageRepository;
    private final UserRepository userRepository;

    // у него соя реализация от Спринга, которая подставится автоматически



    @PostConstruct
    private void fill() {

        Category cat1 = new Category();
        cat1.setCategory("доски для виндсерфинга");
        categoryRepository.save(cat1);

        Good good1 = new Good();
        good1.setPriceBuyed(2.0);
        good1.setPriceOfCellWithoutDiscount(5.0);
        good1.setCategory(cat1);
        goodRepository.save(good1);

        Corsina corsina1 = new Corsina();
        corsinaRepository.save(corsina1);

        Manager manager1 = new Manager();
        manager1.setLogin("prodavan1");
        manager1.setPassword("1");
        manager1.setSalary(100);
        managerRepository.save(manager1);



        Customer c1 = new Customer();
        c1.setLogin("user1");
        c1.setPassword("1");
        c1.setFirstName("саша1");
        c1.setSecondName("борщёв1");
        c1.setMobilePhone("+7(926)8397981");
        c1.setEmail("1000000proekt@list.ru");
        c1.setBirthDay(LocalDateTime.of(1987, 04, 17, 11,0, 0,0));
        c1.setPersonalManager(manager1);
        c1.setCurrentCorsina(corsina1);
        //???????????????
        customerRepository.save(c1);
        // перед ссылками друг на друга сохранить!!!



        Address address1 = new Address();
        address1.setCountry("Israel");
        address1.setRegion("Всем привет");
        address1.setCity("Tel-Aviv");
        address1.setStreet("Disengoff str.");
        address1.setIndex(127018);
        address1.setNumberOfHouse("1");
        address1.setNumberOfAppartmnet("55");
        address1.setCustomer(c1);

        addressRepository.save(address1);


        Storage storage1 = new Storage();
        storage1.setNameOfStorage(StorageNamesEnum.OUR_STORAGE);
        storageRepository.save(storage1);

        //

        StorageGoods storageGoods1 = new StorageGoods();
        storageGoods1.setNumberOfGoodsRemained(25);
        storageGoods1.setGood(good1);
        storageGoods1.setStorage(storage1);
        storageGoodsRepository.save(storageGoods1);
        //

        Driver driver1 = new Driver();
        driver1.setFirstName("Ashot1");
        driver1.setSecondName("Karapetian1");
        driver1.setDriverLicense("1");
        driver1.setMobilePhone("12345");
        driver1.setEmail("karapetian1@mail.ru");
        driver1.setCar("mazda");
        driverRepository.save(driver1);

        Order order1 = new Order();
        order1.setCustomer(c1);
        order1.setDriver(driver1);
        order1.setAddress(address1);
        orderRepository.save(order1);

        //
        OrderGoods orderGoods1 = new OrderGoods();
        orderGoods1.setNumberOfGoods(5);
        orderGoods1.setGood(good1);
        orderGoods1.setOrder(order1);
        orderGoodsRepository.save(orderGoods1);

        //


        //


        //

        CorsinaGoods corsinaGoods1 = new CorsinaGoods();
        corsinaGoods1.setNumberOfGoods(5);
        corsinaGoods1.setGood(good1);
        corsinaGoods1.setCorsina(corsina1);
        corsinaGoodsRepository.save(corsinaGoods1);
        //_________________________________


    }

}
