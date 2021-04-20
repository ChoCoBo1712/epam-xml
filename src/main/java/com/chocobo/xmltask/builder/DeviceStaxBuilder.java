package com.chocobo.xmltask.builder;

import com.chocobo.xmltask.entity.*;
import com.chocobo.xmltask.exception.DeviceException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.YearMonth;

public class DeviceStaxBuilder extends DeviceBuilder {

    private static final char DASH = '-';
    private static final char UNDERSCORE = '_';
    public static final String INTEGRAL_DEVICE_TAG = DeviceXmlTag.INTEGRAL_DEVICE.toString();
    public static final String PERIPHERAL_DEVICE_TAG = DeviceXmlTag.PERIPHERAL_DEVICE.toString();

    @Override
    public void buildSetDevices(String filePath) throws DeviceException {
        XMLStreamReader reader;
        String name;

        try(FileInputStream inputStream = new FileInputStream(filePath)) {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            reader = inputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(INTEGRAL_DEVICE_TAG)) {
                        Device device = new IntegralDevice();
                        buildDevice(device, reader);
                        devices.add(device);
                    }

                    if (name.equals(PERIPHERAL_DEVICE_TAG)) {
                        Device device = new PeripheralDevice();
                        buildDevice(device, reader);
                        devices.add(device);
                    }
                }
            }
        } catch (XMLStreamException | IOException e) {
            throw new DeviceException("XML file reading error: " + filePath, e);
        }
    }

    private void buildDevice(Device device, XMLStreamReader reader) throws XMLStreamException, DeviceException {
        String idAttr = DeviceXmlAttr.DEVICE_ID.toString();
        String colorAttr = DeviceXmlAttr.COLOR.toString();

        String deviceId = reader.getAttributeValue(null, idAttr);
        String color = reader.getAttributeValue(null, colorAttr);

        device.setDeviceId(deviceId);
        if (color != null) {
            device.setColor(color);
        }

        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    String enumName = toEnumName(name);
                    DeviceXmlTag tag = DeviceXmlTag.valueOf(enumName);
                    String data = getXMLText(reader);
                    switch (tag) {
                        case NAME -> device.setName(data);
                        case ORIGIN -> device.setOrigin(DeviceOrigin.valueOf(data));
                        case RELEASE_TIME -> device.setReleaseTime(YearMonth.parse(data));
                        case PRICE -> device.setPrice(Integer.parseInt(data));
                        case POWER_CONSUMPTION -> device.setPowerConsumption(Integer.parseInt(data));
                        case COOLER -> device.setCooler(Boolean.parseBoolean(data));
                        case CRITICAL -> device.setCritical(Boolean.parseBoolean(data));
                        case INTEGRAL_TYPE -> {
                            IntegralDevice integralDevice = (IntegralDevice) device;
                            integralDevice.setDeviceType(IntegralDeviceType.valueOf(data));
                        }
                        case PERIPHERAL_TYPE -> {
                            PeripheralDevice peripheralDevice = (PeripheralDevice) device;
                            peripheralDevice.setDeviceType(PeripheralDeviceType.valueOf(data));
                        }
                        case PORT -> {
                            PeripheralDevice peripheralDevice = (PeripheralDevice) device;
                            peripheralDevice.setDevicePort(DevicePort.valueOf(data));
                        }
                        default -> throw new EnumConstantNotPresentException(
                                tag.getDeclaringClass(), tag.name());
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    if (name.equals(INTEGRAL_DEVICE_TAG) || name.equals(PERIPHERAL_DEVICE_TAG)) {
                        return;
                    }
                }
            }
        }

        throw new DeviceException("Unable to build Device object");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

    private String toEnumName(String string) {
        return string.strip()
                .replace(DASH, UNDERSCORE)
                .toUpperCase();
    }
}
