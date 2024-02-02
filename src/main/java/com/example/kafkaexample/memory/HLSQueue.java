package com.example.kafkaexample.memory;

import java.util.concurrent.LinkedBlockingQueue;

public  class HLSQueue {
    public static LinkedBlockingQueue<Packet> hlsQueue = new LinkedBlockingQueue<Packet>();

    public static boolean doSend2Kafka=false;
}
