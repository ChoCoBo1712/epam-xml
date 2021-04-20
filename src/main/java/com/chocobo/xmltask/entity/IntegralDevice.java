package com.chocobo.xmltask.entity;

import java.time.YearMonth;

public class IntegralDevice extends Device{

    private IntegralDeviceType deviceType;

    public IntegralDevice() {
    }

    public IntegralDevice(
            String deviceId, String color, String name, DeviceOrigin origin, int price, YearMonth releaseTime,
            int powerConsumption, boolean cooler, boolean critical, IntegralDeviceType deviceType
    ) {
        super(deviceId, color, name, origin, price, releaseTime, powerConsumption, cooler, critical);
        this.deviceType = deviceType;
    }

    public IntegralDeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(IntegralDeviceType deviceType) {
        this.deviceType = deviceType;
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

        boolean result = super.equals(device);
        result &= (device.deviceType != null)
                ? device.deviceType.equals(deviceType)
                : device.deviceType == deviceType;
        return result;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 7;

        result = result * prime + super.hashCode();
        result = result * prime + (deviceType == null ? 0 : deviceType.hashCode());

        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());
        stringBuilder.append(", deviceType: ").append(deviceType).append(";");
        return stringBuilder.toString();
    }
}
