package com.chocobo.xmltask.builder;

import com.chocobo.xmltask.exception.DeviceException;

public class DeviceBuilderFactory {

    private enum ParserType {
        DOM, SAX, STAX
    }

    private DeviceBuilderFactory() { }

    public static DeviceBuilder createDeviceBuilder(String parserType) throws DeviceException {
        try {
            ParserType type = ParserType.valueOf(parserType.toUpperCase());

            return switch (type) {
                case DOM -> new DeviceDomBuilder();
                case SAX -> new DeviceSaxBuilder();
                case STAX -> new DeviceStaxBuilder();
            };
        } catch (IllegalArgumentException e) {
            throw new DeviceException("Illegal parser name: " + parserType, e);
        }
    }
}
