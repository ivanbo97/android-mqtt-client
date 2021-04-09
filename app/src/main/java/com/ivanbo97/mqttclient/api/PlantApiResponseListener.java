package com.ivanbo97.mqttclient.api;

import android.util.Log;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class PlantApiResponseListener implements Response.Listener<JSONObject> {

    List<String> jsonResponses;

    public PlantApiResponseListener(List<String> jsonResponses) {
        this.jsonResponses = jsonResponses;
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray jsonArray = response.getJSONArray("data");
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("common_name");

                jsonResponses.add(name);
            }
            Log.i("RETRIEVED DATA:",jsonResponses.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

