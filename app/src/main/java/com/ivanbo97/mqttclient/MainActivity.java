package com.ivanbo97.mqttclient;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import com.ivanbo97.mqttclient.callback.AndroidMqttClientCallback;
import com.ivanbo97.mqttclient.callback.ConnectionTokenCallback;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MainActivity extends AppCompatActivity {



    TextView dataReceived ;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataReceived = (TextView) findViewById(R.id.dataReceived);
        MqttCallbackExtended callback = new AndroidMqttClientCallback(dataReceived);
        String clientId = MqttClient.generateClientId();
        final MqttAndroidClient client =
                new MqttAndroidClient(this.getApplicationContext(), "tcp://broker.hivemq.com:1883",
                        clientId);

        client.setCallback(callback);

        try {
            ConnectionTokenCallback connectionTokenCallback = new ConnectionTokenCallback(client);
            IMqttToken token = client.connect();
            token.setActionCallback(connectionTokenCallback);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}
