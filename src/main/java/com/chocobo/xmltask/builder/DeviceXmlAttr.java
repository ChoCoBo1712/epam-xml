package com.chocobo.xmltask.builder;

public enum DeviceXmlAttr {
    DEVICE_ID,
    COLOR;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
