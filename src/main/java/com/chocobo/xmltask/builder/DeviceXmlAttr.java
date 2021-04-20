package com.chocobo.xmltask.builder;

public enum DeviceXmlAttr {
    DEVICE_ID,
    COLOR;

    private static final char UNDERSCORE = '_';
    private static final char DASH = '-';

    @Override
    public String toString() {
        return name().toLowerCase().replace(UNDERSCORE, DASH);
    }
}
