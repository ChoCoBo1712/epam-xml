package com.chocobo.xmltask.entity;

public enum IntegralDeviceType {
    CPU("CPU"),
    MOTHERBOARD("Motherboard"),
    POWER("Power supply");

    private String value;

    IntegralDeviceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
