package com.chocobo.xmltask.validator;

import com.chocobo.xmltask.exception.DeviceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class DeviceXmlValidator {

    private static final Logger logger = LogManager.getLogger();
    private static final String language = XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI;
    private static final SchemaFactory factory = SchemaFactory.newInstance(language);
    private static final String SCHEMA_NAME = "schema/devices.xsd";
    private static final File SCHEMA_FILE;

    static {
        ClassLoader loader = DeviceXmlValidator.class.getClassLoader();
        SCHEMA_FILE = new File(Objects.requireNonNull(loader.getResource(SCHEMA_NAME)).getFile());
    }

    public static boolean isValidFile(String filePath) throws DeviceException {
        try {
            Schema schema = factory.newSchema(SCHEMA_FILE);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(filePath);
            validator.validate(source);
        } catch (SAXException e) {
            logger.info("File is not valid : " + filePath, e);
            return false;
        } catch (IOException e) {
            throw new DeviceException("Invalid file path: " + filePath, e);
        }
        return true;
    }

}
