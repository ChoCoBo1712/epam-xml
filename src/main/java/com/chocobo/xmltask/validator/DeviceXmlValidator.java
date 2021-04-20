package com.chocobo.xmltask.validator;

import com.chocobo.xmltask.exception.DeviceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.net.URL;

public class DeviceXmlValidator {

    private static final Logger logger = LogManager.getLogger();
    private static final String SCHEMA_NAME = "schema/devices.xsd";
    private static final URL SCHEMA_URL;

    static {
        ClassLoader loader = DeviceXmlValidator.class.getClassLoader();
        SCHEMA_URL = loader.getResource(SCHEMA_NAME);
    }

    public static boolean isValidFile(String filePath) throws DeviceException {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        try {
            Schema schema = factory.newSchema(SCHEMA_URL);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(filePath);
            validator.validate(source);
        } catch (SAXException e) {
            logger.warn("File is not valid : " + filePath, e);
            return false;
        } catch (IOException e) {
            throw new DeviceException("Invalid file path: " + filePath, e);
        }
        logger.info("Valid XML file: " + filePath);
        return true;
    }

}
