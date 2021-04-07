package com.chocobo.xmltask.builder;

import com.chocobo.xmltask.entity.Device;
import com.chocobo.xmltask.exception.DeviceException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

public class DevicesSaxBuilder extends DeviceBuilder {

    private DeviceHandler handler = new DeviceHandler();
    private XMLReader reader;

    public DevicesSaxBuilder() throws DeviceException {
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
    public void buildDevices(String filePath) throws DeviceException {
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
