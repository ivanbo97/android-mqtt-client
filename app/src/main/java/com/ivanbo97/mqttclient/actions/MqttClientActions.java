package com.ivanbo97.mqttclient.actions;

import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.ivanbo97.mqttclient.MainActivity;
import com.ivanbo97.mqttclient.callback.AndroidMqttClientCallback;
import com.ivanbo97.mqttclient.callback.ConnectionTokenCallback;
import com.ivanbo97.mqttclient.chart.SensorDataChart;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttClientActions {

    private MqttAndroidClient mqttClient;


    private LineChart lineChart;
    private SensorDataChart dataChart;
    private TextView dataReceived;
    private MainActivity mainActivity;

    public MqttClientActions(LineChart lineChart, SensorDataChart dataChart, TextView dataReceived, MainActivity mainActivity) {
        this.lineChart = lineChart;
        this.dataChart = dataChart;
        this.dataReceived = dataReceived;
        this.mainActivity = mainActivity;
        clientInit();
    }

    public void clientInit() {
        String clientId = MqttClient.generateClientId();
        mqttClient = new MqttAndroidClient(MainActivity.getMainActivityContext(), "tcp://broker.hivemq.com:1883",
                clientId);
        MqttCallbackExtended callback = new AndroidMqttClientCallback(dataReceived, dataChart, this);

        mqttClient.setCallback(callback);
    }

    public void clientConnect() {
        try {
            ConnectionTokenCallback connectionTokenCallback = new ConnectionTokenCallback(mqttClient, mainActivity);
            IMqttToken token = mqttClient.connect();
            token.setActionCallback(connectionTokenCallback);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
