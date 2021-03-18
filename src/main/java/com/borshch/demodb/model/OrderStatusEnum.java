package com.borshch.demodb.model;

public enum OrderStatusEnum {//ПЕРЕЧИСЛЕНИЕ С ЖЕСТКИМ ВЫБОРОМ КОНСТАНТ
    // не ООП, не наследуются
    //

    CREATED ("создан"),
    PAYED ("оплачен"),
    IN_DELIVERY ("доставляется_сейчас"),
    NON_DELIVERED ("не доставлен"),
    FINISHED ("завершён"),
    CANCELLED ("отменен");

    private String name;

    OrderStatusEnum(String name) { // по умолчанию приватные
        this.name = name;
    }

    public String getName() { // геттер пишем сами, @Data Не работает
        return name;
    }
}
