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
    private final StorageGoodsRepository storageGoodsRepository;
    private final StorageRepository storageRepository;
    private final UserRepository userRepository;

    // у него соя реализация от Спринга, которая подставится автоматически



    @PostConstruct
    private void fill() {



        Customer c1 = new Customer();
        c1.setLogin("user1");
        c1.setPassword("1");
        c1.setFirstName("саша1");
        c1.setSecondName("борщёв1");
        c1.setMobilePhone("+7-926-839-7981");
        c1.setEmail("1000000proekt@list.ru");
        c1.setBirthDay(LocalDateTime.of(1987, 04, 17, 11,0, 0,0));
        // как дату, я хз
        customerRepository.save(c1);
        // перед ссылками друг на друга сохранить!!!



        Address address1 = new Address();
        address1.setCountry("Israel");
        address1.setCity("Tel-Aviv");
        address1.setStreet("Disengoff str.");
        address1.setIndex(127018);
        address1.setNumberOfHouse("1");
        address1.setNumberOfAppartmnet("55");
        address1.setCustomer(c1);
        addressRepository.save(address1);



        Category cat1 = new Category();
        cat1.setCategory("доски для виндсерфинга");
        categoryRepository.save(cat1);



        Good good1 = new Good();
        good1.setPriceBuyed(1.0);
        good1.setPriceOfCellWithoutDiscount(5.0);
        good1.setCategory(cat1);
        goodRepository.save(good1);
        //


        Manager manager1 = new Manager();
        manager1.setLogin("prodavan1");
        manager1.setPassword("1");
        manager1.setSalary(100);
        c1.setPersonalManager(manager1);
        managerRepository.save(manager1);


        //

        Storage storage1 = new Storage();
        storage1.setNameOfStorage(StorageNamesEnum.OUR_STORAGE);
        storageRepository.save(storage1);

        //

        StorageGoods storageGoods1 = new StorageGoods();
        storageGoods1.setNumberOfGoodsRemained(25);
        storageGoodsRepository.save(storageGoods1);

        //


        storageGoods1.setGood(good1);
        storageGoods1.setStorage(storage1);

        //

        Driver driver1 = new Driver();
        driver1.setFirstName("Ashot1");
        driver1.setSecondName("Karapetian1");
        driver1.setDriverLicense("1");
        driver1.setMobilePhone("12345");
        driver1.setEmail("karapetian1@mail.ru");
        driver1.setCar("mazda");

        driverRepository.save(driver1);
        //
        Corsina corsina1 = new Corsina();
        corsinaRepository.save(corsina1);
        c1.setCurrentCorsina(corsina1);

        //

        CorsinaGoods corsinaGoods1 = new CorsinaGoods();
        corsinaGoods1.setNumberOfGoods(5);
        corsinaGoodsRepository.save(corsinaGoods1);
        corsinaGoods1.setGood(good1);
        corsinaGoods1.setCorsina(corsina1);

        //

        Order order1 = new Order();
        orderRepository.save(order1);
        order1.setCustomer(c1);
        order1.setDriver(driver1);
        order1.setAddress(address1);

        //

        OrderGoods orderGoods1 = new OrderGoods();
        orderGoods1.setNumberOfGoods(5);
        orderGoods1.setGood(good1);
        orderGoods1.setOrder(order1);

        //_________________________________

        Customer c2 = new Customer();
        c2.setLogin("user2");
        c2.setPassword("2");
        c2.setFirstName("борман");
        c2.setSecondName("дмитрий");
        c2.setMobilePhone("+7-926-593-2361");
        c2.setEmail("borman@gmail.com.ru");
        c2.setBirthDay(LocalDateTime.of(1985, 10, 25, 11,0, 0,0));
        customerRepository.save(c2);
        // перед ссылками друг на друга сохранить!!!



        Address address2 = new Address();
        address2.setCountry("Canada");
        address2.setCity("Ontario");
        address2.setStreet("George Washington str.");
        address2.setIndex(127018);
        address2.setNumberOfHouse("1");
        address2.setNumberOfAppartmnet("3");
        addressRepository.save(address2);
        address2.setCustomer(c2);



        Category cat2 = new Category();
        cat2.setCategory("тапки для душа");
        categoryRepository.save(cat2);



        Good good2 = new Good();
        good2.setPriceBuyed(0.5);
        good2.setPriceOfCellWithoutDiscount(2.5);
        goodRepository.save(good2);
        //
        good2.setCategory(cat2);

        Manager manager2 = new Manager();
        manager2.setLogin("prodavan2");
        manager1.setPassword("2");
        manager1.setSalary(50);

        managerRepository.save(manager2);
        c1.setPersonalManager(manager2);

        //

        Storage storage2 = new Storage();
        storage2.setNameOfStorage(StorageNamesEnum.OUR_STORAGE);
        storageRepository.save(storage2);

        //

        StorageGoods storageGoods2 = new StorageGoods();
        storageGoods2.setNumberOfGoodsRemained(25);
        storageGoodsRepository.save(storageGoods1);

        //


        storageGoods2.setGood(good2);
        storageGoods2.setStorage(storage2);

        //

        Driver driver2 = new Driver();
        driver2.setFirstName("Aram2");
        driver2.setSecondName("Karapetian2");
        driver2.setDriverLicense("1");
        driver2.setMobilePhone("12345");
        driver2.setEmail("karapetian1@mail.ru");
        driver2.setCar("mazda");

        driverRepository.save(driver2);
        //
        Corsina corsina2 = new Corsina();
        corsinaRepository.save(corsina2);
        c2.setCurrentCorsina(corsina2);

        //

        CorsinaGoods corsinaGoods2 = new CorsinaGoods();
        corsinaGoods2.setNumberOfGoods(5);
        corsinaGoodsRepository.save(corsinaGoods2);
        corsinaGoods2.setGood(good2);
        corsinaGoods2.setCorsina(corsina2);

        //

        Order order2 = new Order();
        orderRepository.save(order2);
        order1.setCustomer(c2);
        order1.setDriver(driver2);
        order1.setAddress(address2);

        //

        OrderGoods orderGoods2 = new OrderGoods();
        orderGoods2.setNumberOfGoods(5);
        orderGoods2.setGood(good2);
        orderGoods2.setOrder(order2);

        //----------------------------------

        Customer c3 = new Customer();
        c3.setLogin("user3");
        c3.setPassword("3");
        c3.setFirstName("саша3");
        c3.setSecondName("борщёв3");
        c3.setMobilePhone("+7-926-839-7983");
        c3.setEmail("3000000proekt@list.ru");
        c3.setBirthDay(LocalDateTime.of(1985, 04, 07, 23,0, 0,0));
        customerRepository.save(c3);
// перед ссылками друг на друга сохранить!!!



        Address address3 = new Address();
        address3.setCountry("Israel");
        address3.setCity("Tel-Aviv");
        address3.setStreet("Disengoff str.");
        address3.setIndex(327038);
        address3.setNumberOfHouse("3");
        address3.setNumberOfAppartmnet("55");
        addressRepository.save(address3);
        address3.setCustomer(c3);



        Category cat3 = new Category();
        cat3.setCategory("скайбординг");
        categoryRepository.save(cat3);



        Good good3 = new Good();
        good3.setPriceBuyed(3.0);
        good3.setPriceOfCellWithoutDiscount(5.0);
        goodRepository.save(good3);
//
        good3.setCategory(cat3);

        Manager manager3 = new Manager();
        manager3.setLogin("prodavan3");
        manager3.setPassword("3");
        manager3.setSalary(300);

        managerRepository.save(manager3);
        c3.setPersonalManager(manager3);

//

        Storage storage3 = new Storage();
        storage3.setNameOfStorage(StorageNamesEnum.OUR_STORAGE);
        storageRepository.save(storage3);

//

        StorageGoods storageGoods3 = new StorageGoods();
        storageGoods3.setNumberOfGoodsRemained(25);
        storageGoodsRepository.save(storageGoods3);

//


        storageGoods3.setGood(good3);
        storageGoods3.setStorage(storage3);

//

        Driver driver3 = new Driver();
        driver3.setFirstName("Ashot3");
        driver3.setSecondName("Karapetian3");
        driver3.setDriverLicense("3");
        driver3.setMobilePhone("32345");
        driver3.setEmail("karapetian3@mail.ru");
        driver3.setCar("mazda");

        driverRepository.save(driver3);
//
        Corsina corsina3 = new Corsina();
        corsinaRepository.save(corsina3);
        c3.setCurrentCorsina(corsina3);

//

        CorsinaGoods corsinaGoods3 = new CorsinaGoods();
        corsinaGoods3.setNumberOfGoods(5);
        corsinaGoodsRepository.save(corsinaGoods3);
        corsinaGoods3.setGood(good3);
        corsinaGoods3.setCorsina(corsina3);

//

        Order order3 = new Order();
        orderRepository.save(order3);
        order3.setCustomer(c3);
        order3.setDriver(driver3);
        order3.setAddress(address3);

//

        OrderGoods orderGoods3 = new OrderGoods();
        orderGoods3.setNumberOfGoods(5);
        orderGoods3.setGood(good3);
        orderGoods3.setOrder(order3);





    }

}
