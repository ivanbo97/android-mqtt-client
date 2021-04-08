package com.ivanbo97.mqttclient.callback;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.ivanbo97.mqttclient.MainActivity;
import com.ivanbo97.mqttclient.actions.MqttClientActions;
import com.ivanbo97.mqttclient.chart.SensorDataChart;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class AndroidMqttClientCallback implements MqttCallbackExtended {

    private TextView handleToTextViewComponent;
    private SensorDataChart sensorDataChart;
    private MqttClientActions mqttClientActions;

    public AndroidMqttClientCallback(TextView handleToTextViewComponent,
                                     SensorDataChart sensorDataChart,
                                     MqttClientActions mqttClientActions) {
        this.handleToTextViewComponent = handleToTextViewComponent;
        this.sensorDataChart = sensorDataChart;
        this.mqttClientActions = mqttClientActions;
    }

    @Override
    public void connectComplete(boolean reconnect, String serverURI) {
        Log.w("Debug","Connected");
    }

    @Override
    public void connectionLost(Throwable cause) {
        MainActivity.connectionLostToast();
        mqttClientActions.clientConnect();
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        Log.w("Debug",message.toString());
        handleToTextViewComponent.setText(message.toString());
        sensorDataChart.addEntry(Float.valueOf(message.toString()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}

