package com.ivanbo97.mqttclient.callback;

import android.util.Log;
import android.widget.TextView;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class AndroidMqttClientCallback implements MqttCallbackExtended {

    private TextView handleToTextViewComponent;

    public AndroidMqttClientCallback(TextView handleToTextViewComponent) {
        this.handleToTextViewComponent = handleToTextViewComponent;
    }

    @Override
    public void connectComplete(boolean reconnect, String serverURI) {
        Log.w("Debug","Connected");
    }

    @Override
    public void connectionLost(Throwable cause) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        Log.w("Debug",message.toString());
        handleToTextViewComponent.setText(message.toString());
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}

