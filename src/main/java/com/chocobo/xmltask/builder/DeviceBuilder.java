package com.chocobo.xmltask.builder;

import com.chocobo.xmltask.entity.Device;
import com.chocobo.xmltask.exception.DeviceException;

import java.util.HashSet;
import java.util.Set;

public abstract class DeviceBuilder {

    protected Set<Device> devices;

    public DeviceBuilder() {
        devices = new HashSet<>();
    }

    public DeviceBuilder(Set<Device> devices) {
        this.devices = devices;
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public abstract void buildSetDevices(String xmlPath) throws DeviceException;
}
