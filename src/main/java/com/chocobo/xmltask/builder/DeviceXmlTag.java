package com.chocobo.xmltask.builder;

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
    INTEGRAL_TYPE,
    PERIPHERAL_TYPE;

    private static final char UNDERSCORE = '_';
    private static final char DASH = '-';

    @Override
    public String toString() {
        return name().toLowerCase().replace(UNDERSCORE, DASH);
    }
}
