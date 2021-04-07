package com.chocobo.xmltask.builder;

public enum DeviceXmlAttr {
    ID,
    COLOR;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
