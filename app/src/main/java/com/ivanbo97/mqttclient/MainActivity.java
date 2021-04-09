package com.ivanbo97.mqttclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.ivanbo97.mqttclient.actions.MqttClientActions;
import com.ivanbo97.mqttclient.chart.SensorDataChart;

import org.eclipse.paho.client.mqttv3.MqttException;

public class MainActivity extends AppCompatActivity {

    private TextView dataReceived;
    private LineChart lineChart;
    private SensorDataChart dataChart;
    private static Context mainActivityContext;
    private MqttClientActions mqttClientActions;

    public static Context getMainActivityContext() {
        return mainActivityContext;
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityContext = getApplicationContext();
        setContentView(R.layout.activity_main);
        dataReceived = (TextView) findViewById(R.id.dataReceived);
        lineChart = (LineChart) findViewById(R.id.chart);
        dataChart = new SensorDataChart(lineChart);
        mqttClientActions = new MqttClientActions(lineChart, dataChart, dataReceived, this);
        mqttClientActions.clientConnect();
    }

    public static void connectionLostToast() {
        Toast toast = Toast.makeText(mainActivityContext, "Lost connection to mqtt broker...Trying to reconnect...", Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void connectionToBrokerFailed() {
        Toast toast = Toast.makeText(mainActivityContext, "Connection to broker failed. Please check your network connection.", Toast.LENGTH_SHORT);
        toast.show();

    }

    public MqttClientActions getMqttClientActions() {
        return mqttClientActions;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            mqttClientActions.getMqttClient().disconnect(2);
        } catch (MqttException e){
            e.printStackTrace();
        }
    }
}
