package com.chocobo.xmltask.builder;

import com.chocobo.xmltask.entity.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.YearMonth;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class DeviceHandler extends DefaultHandler {

    private static final char DASH = '-';
    private static final char UNDERSCORE = '_';

    private Set<Device> devices;
    private Device currentDevice;
    private DeviceXmlTag currentTag;
    private EnumSet<DeviceXmlTag> tagsWithText;

    public DeviceHandler() {
        devices = new HashSet<>();
        tagsWithText = EnumSet.range(DeviceXmlTag.NAME, DeviceXmlTag.PERIPHERAL_TYPE);
    }

    public Set<Device> getDevices() {
        return devices;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        String integralDeviceTag = DeviceXmlTag.INTEGRAL_DEVICE.toString();
        String peripheralDeviceTag = DeviceXmlTag.PERIPHERAL_DEVICE.toString();
        String idAttr = DeviceXmlAttr.ID.toString();

        if (integralDeviceTag.equals(qName) || peripheralDeviceTag.equals(qName)) {
            currentDevice = integralDeviceTag.equals(qName) ? new IntegralDevice() : new PeripheralDevice();

            String firstAttrName = attrs.getQName(0);
            String firstAttrValue = attrs.getValue(0);
            String secondAttrValue = attrs.getValue(1);

            if (idAttr.equals(firstAttrName)) {
                currentDevice.setId(firstAttrValue);
                currentDevice.setColor(secondAttrValue != null ? secondAttrValue : Device.DEFAULT_COLOR);
            } else {
                currentDevice.setColor(firstAttrValue);
                currentDevice.setColor(secondAttrValue);
            }
        } else {
            String enumName = toEnumName(qName);
            DeviceXmlTag tag = DeviceXmlTag.valueOf(enumName);
            if (tagsWithText.contains(tag)) {
                currentTag = tag;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        String integralDeviceTag = DeviceXmlTag.INTEGRAL_DEVICE.toString();
        String peripheralDeviceTag = DeviceXmlTag.PERIPHERAL_DEVICE.toString();

        if (integralDeviceTag.equals(qName) || peripheralDeviceTag.equals(qName)) {
            devices.add(currentDevice);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length);

        if (currentTag != null) {
            switch (currentTag) {
                case NAME -> currentDevice.setName(data);
                case ORIGIN -> currentDevice.setOrigin(DeviceOrigin.valueOf(data));
                case RELEASE_TIME -> currentDevice.setReleaseTime(YearMonth.parse(data));
                case PRICE -> currentDevice.setPrice(Integer.parseInt(data));
                case POWER_CONSUMPTION -> currentDevice.setPowerConsumption(Integer.parseInt(data));
                case COOLER -> currentDevice.setCooler(Boolean.parseBoolean(data));
                case CRITICAL -> currentDevice.setCritical(Boolean.parseBoolean(data));
                case INTEGRAL_TYPE -> {
                    IntegralDevice device = (IntegralDevice) currentDevice;
                    device.setDeviceType(IntegralDeviceType.valueOf(data));
                }
                case PERIPHERAL_TYPE -> {
                    PeripheralDevice device = (PeripheralDevice) currentDevice;
                    device.setDeviceType(PeripheralDeviceType.valueOf(data));
                }
                case PORT -> {
                    PeripheralDevice device = (PeripheralDevice) currentDevice;
                    device.setDevicePort(DevicePort.valueOf(data));
                }
                default -> throw new EnumConstantNotPresentException(
                        currentTag.getDeclaringClass(), currentTag.name());
            }
        }
        currentTag = null;
    }

    private String toEnumName(String string) {
        return string.strip().replace(DASH, UNDERSCORE).toUpperCase();
    }
}
