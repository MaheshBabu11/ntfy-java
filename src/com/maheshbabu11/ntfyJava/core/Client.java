package com.maheshbabu11.ntfyJava.core;

public interface Client {
    public String sendNotification(String topic,String message);

    public String sendNotification(String topic,String message, String host);


}
