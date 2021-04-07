package com.chocobo.xmltask.entity;

import java.time.YearMonth;
import java.util.Objects;

public abstract class Device {

    public static final String DEFAULT_COLOR = "Black";

    private String id;
    private String color;
    private String name;
    private DeviceOrigin origin;
    private int price;
    private YearMonth releaseTime;
    private int powerConsumption;
    private boolean cooler;
    private boolean critical;

    public Device(
            String id, String color, String name, DeviceOrigin origin, int price,
            YearMonth releaseTime, int powerConsumption, boolean cooler, boolean critical
    ) {
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

        return Objects.equals(id, device.id) && Objects.equals(color, device.color)
                && Objects.equals(name, device.name) && Objects.equals(origin, device.origin)
                && Objects.equals(releaseTime, device.releaseTime) && price == device.price
                && powerConsumption == device.powerConsumption && cooler == device.cooler
                && critical == device.critical;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 7;

        result = prime * result + id.hashCode();
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
        return "id: " + id + ", name: " + name + ", color: " + color + ", origin: " + origin.getValue()
                + ", releaseTime: " + releaseTime.toString() + ", price: " + price
                + ", powerConsumption: " + powerConsumption + ", cooler: " + cooler + ", critical: " + critical;
    }
}
