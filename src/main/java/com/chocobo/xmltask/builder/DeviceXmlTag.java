package com.chocobo.xmltask.builder;

import java.util.Locale;

public enum DeviceXmlTag {
    DEVICES,
    PERIPHERAL_DEVICE,
    INTEGRAL_DEVICE,
    NAME,
    ORIGIN,
    PRICE,
    RELEASE_TIME,
    POWER_CONSUMPTION,
    COOLER,
    CRITICAL,
    PORT,
    TYPE;

    private static final char UNDERSCORE = '_';
    private static final char DASH = '-';

    @Override
    public String toString() {
        return name().toLowerCase().replace(UNDERSCORE, DASH);
    }
}
