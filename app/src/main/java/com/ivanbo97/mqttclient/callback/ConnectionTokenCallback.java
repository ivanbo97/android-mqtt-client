package com.ivanbo97.mqttclient.callback;

import android.util.Log;

import com.ivanbo97.mqttclient.MainActivity;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttException;

public class ConnectionTokenCallback implements IMqttActionListener {

    private MqttAndroidClient client;
    MainActivity mainActivity;

    public ConnectionTokenCallback(MqttAndroidClient client,MainActivity mainActivity) {
        this.client = client;
        this.mainActivity = mainActivity;
    }

    @Override
    public void onSuccess(IMqttToken asyncActionToken) {
        Log.d("INFO", "onSuccess");
        try {
            SubscriptionCallback subscriptionCallback = new SubscriptionCallback();
            client.subscribe("sensor/vakata", 0, null,subscriptionCallback);
        } catch (MqttException ex) {
            System.err.println("Exception while subscribing");
            ex.printStackTrace();
        }
    }

    @Override
    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
        // Something went wrong e.g. connection timeout or firewall problems
        Log.d("INFO", "onFailure");
        MainActivity.connectionToBrokerFailed();
        mainActivity.getMqttClientActions().clientConnect();
    }
}
