package com.borshch.demodb.model;

public enum StorageNamesEnum {

    OUR_STORAGE("наш склад"),
    IN_OFFICE("в офисе"),
    IN_OTHER_STORAGE1("на складе 1"),
    IN_OTHER_STORAGE5("на складе 5")
    ;

    private String name;

    StorageNamesEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

