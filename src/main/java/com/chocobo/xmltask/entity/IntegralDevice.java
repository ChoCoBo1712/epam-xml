package com.chocobo.xmltask.entity;

import java.time.YearMonth;
import java.util.Objects;

public class IntegralDevice extends Device{

    private IntegralDeviceType deviceType;
    private DevicePort devicePort;

    public IntegralDevice(
            String id, String color, String name, DeviceOrigin origin, int price, YearMonth releaseTime,
            int powerConsumption, boolean cooler, boolean critical, IntegralDeviceType deviceType,
            DevicePort devicePort
    ) {
        super(id, color, name, origin, price, releaseTime, powerConsumption, cooler, critical);
        this.deviceType = deviceType;
        this.devicePort = devicePort;
    }

    public IntegralDeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(IntegralDeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public DevicePort getDevicePort() {
        return devicePort;
    }

    public void setDevicePort(DevicePort devicePort) {
        this.devicePort = devicePort;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        IntegralDevice device = (IntegralDevice) object;

        return super.equals(device) && Objects.equals(deviceType, device.deviceType)
                && Objects.equals(devicePort, device.devicePort);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 7;

        result = result * prime + super.hashCode();
        result = result * prime + deviceType.hashCode();
        result = result * prime + devicePort.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return super.toString() + ", devicePort" + devicePort.getValue() + ", deviceType: " + deviceType.getValue();
    }
}
