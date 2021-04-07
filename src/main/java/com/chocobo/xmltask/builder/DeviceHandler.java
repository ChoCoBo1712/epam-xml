package com.chocobo.xmltask.builder;

import com.chocobo.xmltask.entity.Device;
import com.chocobo.xmltask.entity.IntegralDevice;
import com.chocobo.xmltask.entity.PeripheralDevice;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class DeviceHandler extends DefaultHandler {

    private Set<Device> devices;
    private Device currentDevice;
    private DeviceXmlTag currentTag;
    private EnumSet<DeviceXmlTag> tagsWithText;

    public DeviceHandler() {
        devices = new HashSet<>();
        tagsWithText = EnumSet.range(DeviceXmlTag.NAME, DeviceXmlTag.TYPE);
    }

    public Set<Device> getDevices() {
        return devices;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        String integralDeviceTag = DeviceXmlTag.INTEGRAL_DEVICE.toString();
        String peripheralDeviceTag = DeviceXmlTag.PERIPHERAL_DEVICE.toString();
        String idAttr = DeviceXmlAttr.ID.toString();
        String colorAttr = DeviceXmlAttr.COLOR.toString();

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


        }
    }
}
