package com.bennero.common.messages;

public class SensorUpdateInfo {
    private byte sensorId;
    private float value;

    public SensorUpdateInfo(byte sensorId, float value) {
        this.sensorId = sensorId;
        this.value = value;
    }

    public byte getSensorId() {
        return sensorId;
    }

    public float getValue() {
        return value;
    }
}