package com.borshch.demodb.model;

public enum AvailibilityEnum {

    ONSTORAGE ("на складе"),
    ON_EXTERNAL_STORAGE("на чужом складе"),
    NOT_AVAILABLE("недоступен");

    private String name;
    AvailibilityEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
