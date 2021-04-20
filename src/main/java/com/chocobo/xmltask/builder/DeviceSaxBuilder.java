package com.chocobo.xmltask.builder;

import com.chocobo.xmltask.exception.DeviceException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class DeviceSaxBuilder extends DeviceBuilder {

    private final DeviceHandler handler = new DeviceHandler();
    private final XMLReader reader;

    public DeviceSaxBuilder() throws DeviceException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            reader = parser.getXMLReader();
            reader.setErrorHandler(new DeviceErrorHandler());
            reader.setContentHandler(handler);
        } catch (SAXException | ParserConfigurationException e) {
            throw new DeviceException("SAX parser configuration error", e);
        }
    }

    @Override
    public void buildSetDevices(String filePath) throws DeviceException {
        try {
            reader.parse(filePath);
        } catch (IOException e) {
            throw new DeviceException("XML file reading error: " + filePath, e);
        } catch (SAXException e) {
            throw new DeviceException("XML file parsing error: " + filePath, e);
        }
        devices = handler.getDevices();
    }
}
