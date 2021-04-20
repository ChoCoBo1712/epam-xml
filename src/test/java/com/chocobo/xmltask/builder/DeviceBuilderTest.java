package com.chocobo.xmltask.builder;

import com.chocobo.xmltask.entity.*;
import com.chocobo.xmltask.exception.DeviceException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;

public class DeviceBuilderTest {

    private Set<Device> expected;

    @BeforeClass
    public void setUp() {
        expected = new HashSet<>();
        for (int i = 1; i <= 8; i++) {
            PeripheralDevice device = new PeripheralDevice("peripheral" + i, "Blue", "Peripheral example",
                    DeviceOrigin.USA, 100, YearMonth.parse("2020-12"), 100, false,
                    true, PeripheralDeviceType.IO, DevicePort.USB);
            expected.add(device);
        }
        for (int i = 1; i <= 8; i++) {
            IntegralDevice device = new IntegralDevice("integral" + i, "Black", "Integral example",
                    DeviceOrigin.CHINA, 1000, YearMonth.parse("2016-11"), 1000, true,
                    true, IntegralDeviceType.CPU);
            expected.add(device);
        }
    }

    @DataProvider(name = "builder-provider")
    public Object[][] builderDataProvider() throws DeviceException {
        ClassLoader loader = getClass().getClassLoader();
        URL resource = loader.getResource("data/valid.xml");
        String filePath = new File(resource.getFile()).getAbsolutePath();
        return new Object[][] {
                {
                    DeviceBuilderFactory.createDeviceBuilder("SAX"), filePath
                },
                {
                    DeviceBuilderFactory.createDeviceBuilder("DOM"), filePath
                },
                {
                    DeviceBuilderFactory.createDeviceBuilder("STAX"), filePath
                }
        };
    }

    @Test(dataProvider = "builder-provider")
    public void buildSetDevicesTest(DeviceBuilder builder, String filePath) throws DeviceException {
        builder.buildSetDevices(filePath);
        Set<Device> actual = builder.getDevices();
        Assert.assertEquals(actual, expected);
    }
}
