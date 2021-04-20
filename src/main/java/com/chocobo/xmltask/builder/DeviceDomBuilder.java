package com.chocobo.xmltask.builder;

import com.chocobo.xmltask.entity.*;
import com.chocobo.xmltask.exception.DeviceException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.YearMonth;

public class DeviceDomBuilder extends DeviceBuilder {

    private final DocumentBuilder documentBuilder;

    public DeviceDomBuilder() throws DeviceException {
        super();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new DeviceException("DOM parser configuration error", e);
        }
    }

    @Override
    public void buildSetDevices(String filePath) throws DeviceException {
        Document document;
        try {
            document = documentBuilder.parse(filePath);
            Element root = document.getDocumentElement();

            String integralDevicesTag = DeviceXmlTag.INTEGRAL_DEVICE.toString();
            String peripheralDevicesTag = DeviceXmlTag.PERIPHERAL_DEVICE.toString();
            NodeList integralDevices = root.getElementsByTagName(integralDevicesTag);
            NodeList peripheralDevices = root.getElementsByTagName(peripheralDevicesTag);

            for (int i = 0; i < integralDevices.getLength(); i++) {
                Element element = (Element) integralDevices.item(i);
                Device device = new IntegralDevice();
                buildDevice(element, device);
                devices.add(device);
            }

            for (int i = 0; i < peripheralDevices.getLength(); i++) {
                Element element = (Element) peripheralDevices.item(i);
                Device device = new PeripheralDevice();
                buildDevice(element, device);
                devices.add(device);
            }
        } catch (IOException e) {
            throw new DeviceException("XML file reading error: " + filePath, e);
        } catch (SAXException e) {
            throw new DeviceException("XML file parsing error: " + filePath, e);
        }
    }

    private void buildDevice(Element element, Device device) {
        String idAttribute = DeviceXmlAttr.DEVICE_ID.toString();
        String colorAttribute = DeviceXmlAttr.COLOR.toString();
        String nameTag = DeviceXmlTag.NAME.toString();
        String originTag = DeviceXmlTag.ORIGIN.toString();
        String priceTag = DeviceXmlTag.PRICE.toString();
        String releaseTimeTag = DeviceXmlTag.RELEASE_TIME.toString();
        String powerConsumptionTag = DeviceXmlTag.POWER_CONSUMPTION.toString();
        String coolerTag = DeviceXmlTag.COOLER.toString();
        String criticalTag = DeviceXmlTag.CRITICAL.toString();

        String color = element.getAttribute(colorAttribute);
        color = color.isBlank() ? Device.DEFAULT_COLOR : color;

        device.setDeviceId(element.getAttribute(idAttribute));
        device.setColor(color);
        device.setName(getElementTextContent(element, nameTag));
        device.setOrigin(DeviceOrigin.valueOf(getElementTextContent(element, originTag)));
        device.setPrice(Integer.parseInt(getElementTextContent(element, priceTag)));
        device.setReleaseTime(YearMonth.parse(getElementTextContent(element, releaseTimeTag)));
        device.setPowerConsumption(Integer.parseInt(getElementTextContent(element, powerConsumptionTag)));
        device.setCooler(Boolean.parseBoolean(getElementTextContent(element, coolerTag)));
        device.setCritical(Boolean.parseBoolean(getElementTextContent(element, criticalTag)));

        if (device instanceof IntegralDevice) {
            IntegralDevice integralDevice = (IntegralDevice) device;
            String deviceTypeTag = DeviceXmlTag.INTEGRAL_TYPE.toString();
            integralDevice.setDeviceType(IntegralDeviceType.valueOf(getElementTextContent(element, deviceTypeTag)));
        } else {
            PeripheralDevice peripheralDevice = (PeripheralDevice) device;
            String deviceTypeTag = DeviceXmlTag.PERIPHERAL_TYPE.toString();
            String portTag = DeviceXmlTag.PORT.toString();
            peripheralDevice.setDeviceType(PeripheralDeviceType.valueOf(getElementTextContent(element, deviceTypeTag)));
            peripheralDevice.setDevicePort(DevicePort.valueOf(getElementTextContent(element, portTag)));
        }
    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }
}
