package com.chocobo.xmltask.entity;

public enum DevicePort {
    COM("COM"),
    USB("USB"),
    LPT("LPT");

    private String value;

    DevicePort(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
