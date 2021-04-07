package com.chocobo.xmltask.entity;

public enum DeviceOrigin {
    USA("USA"),
    CHINA("China"),
    JAPAN("Japan"),
    GERMANY("Germany");

    private String value;

    DeviceOrigin(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
