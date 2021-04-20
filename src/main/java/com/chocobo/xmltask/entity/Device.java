package com.chocobo.xmltask.entity;

import java.time.YearMonth;
import java.util.Objects;

public abstract class Device {

    public static final String DEFAULT_COLOR = "Black";

    private String deviceId;
    private String color;
    private String name;
    private DeviceOrigin origin;
    private int price;
    private YearMonth releaseTime;
    private int powerConsumption;
    private boolean cooler;
    private boolean critical;

    public Device(
            String deviceId, String color, String name, DeviceOrigin origin, int price,
            YearMonth releaseTime, int powerConsumption, boolean cooler, boolean critical
    ) {
        this.deviceId = deviceId;
        this.color = color;
        this.name = name;
        this.origin = origin;
        this.price = price;
        this.releaseTime = releaseTime;
        this.powerConsumption = powerConsumption;
        this.cooler = cooler;
        this.critical = critical;
    }

    public Device() {
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String id) {
        this.deviceId = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeviceOrigin getOrigin() {
        return origin;
    }

    public void setOrigin(DeviceOrigin origin) {
        this.origin = origin;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public YearMonth getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(YearMonth releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(int powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public boolean isCooler() {
        return cooler;
    }

    public void setCooler(boolean cooler) {
        this.cooler = cooler;
    }

    public boolean isCritical() {
        return critical;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Device device = (Device) object;

        return Objects.equals(deviceId, device.deviceId) && Objects.equals(color, device.color)
                && Objects.equals(name, device.name) && Objects.equals(origin, device.origin)
                && Objects.equals(releaseTime, device.releaseTime) && price == device.price
                && powerConsumption == device.powerConsumption && cooler == device.cooler
                && critical == device.critical;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 7;

        result = prime * result + deviceId.hashCode();
        result = prime * result + color.hashCode();
        result = prime * result + name.hashCode();
        result = prime * result + origin.hashCode();
        result = prime * result + releaseTime.hashCode();
        result = prime * result + Integer.hashCode(price);
        result = prime * result + Integer.hashCode(powerConsumption);
        result = prime * result + Boolean.hashCode(cooler);
        result = prime * result + Boolean.hashCode(critical);

        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("deviceId: ").append(deviceId);
        stringBuilder.append(", name: ").append(name);
        stringBuilder.append(", color: ").append(color);
        stringBuilder.append(", origin: ").append(origin);
        stringBuilder.append(", releaseTime: ").append(releaseTime);
        stringBuilder.append(", price").append(price);
        stringBuilder.append(", powerConsumption").append(powerConsumption);
        stringBuilder.append(", cooler: ").append(cooler);
        stringBuilder.append(", critical: ").append(critical);
        return stringBuilder.toString();
    }
}
