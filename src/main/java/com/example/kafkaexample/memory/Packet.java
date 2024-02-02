package com.example.kafkaexample.memory;

public class Packet {
    public Packet(){}

    public Packet(String key, byte[] data){
        this.key=key;
        this.data=data;
    }
    private String key;
    private byte[] data;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
