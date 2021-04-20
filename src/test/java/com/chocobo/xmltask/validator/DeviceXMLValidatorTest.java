package com.chocobo.xmltask.validator;

import com.chocobo.xmltask.exception.DeviceException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URL;

public class DeviceXMLValidatorTest {

    @Test
    public void isValidFileValidTest() throws DeviceException {
        ClassLoader loader = getClass().getClassLoader();
        URL resource = loader.getResource("data/valid.xml");

        boolean actual = DeviceXmlValidator.isValidFile(resource.getPath());
        Assert.assertTrue(actual);
    }

    @Test
    public void isValidFileInvalidTest() throws DeviceException {
        ClassLoader loader = getClass().getClassLoader();
        URL resource = loader.getResource("data/invalid.xml");

        boolean actual = DeviceXmlValidator.isValidFile(resource.getPath());
        Assert.assertFalse(actual);
    }
}
