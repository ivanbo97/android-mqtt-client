package com.ivanbo97.mqttclient.callback;

import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

public class SubscriptionCallback implements IMqttActionListener {
    @Override
    public void onSuccess(IMqttToken asyncActionToken) {
        Log.w("Mqtt","Subscribed!");
    }

    @Override
    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
        Log.w("Mqttt", "Subscribed fail!");
    }
}
