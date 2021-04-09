package com.ivanbo97.mqttclient.api;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public class PlantApiErrorListener implements Response.ErrorListener {
    @Override
    public void onErrorResponse(VolleyError error) {
        error.printStackTrace();
    }
}
