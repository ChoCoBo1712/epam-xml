package com.chocobo.xmltask.entity;

import java.time.YearMonth;
import java.util.Objects;

public class PeripheralDevice extends Device{

    private PeripheralDeviceType deviceType;
    private DevicePort devicePort;

    public PeripheralDevice() {
    }

    public PeripheralDevice(
            String deviceId, String color, String name, DeviceOrigin origin, int price, YearMonth releaseTime,
            int powerConsumption, boolean cooler, boolean critical, PeripheralDeviceType deviceType,
            DevicePort devicePort
    ) {
        super(deviceId, color, name, origin, price, releaseTime, powerConsumption, cooler, critical);
        this.deviceType = deviceType;
        this.devicePort = devicePort;
    }

    public PeripheralDeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(PeripheralDeviceType deviceType) {
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

        PeripheralDevice device = (PeripheralDevice) object;

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
        StringBuilder stringBuilder = new StringBuilder(super.toString());
        stringBuilder.append(", devicePort").append(devicePort);
        stringBuilder.append(", deviceType: ").append(deviceType).append(";");
        return stringBuilder.toString();
    }
}
