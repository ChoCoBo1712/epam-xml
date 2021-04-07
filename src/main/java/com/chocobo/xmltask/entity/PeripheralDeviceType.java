package com.chocobo.xmltask.entity;

public enum PeripheralDeviceType {
    IO("IO"),
    MULTIMEDIA("Multimedia");

    private String value;

    PeripheralDeviceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
