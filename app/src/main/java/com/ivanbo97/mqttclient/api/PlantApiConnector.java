package com.ivanbo97.mqttclient.api;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ivanbo97.mqttclient.MainActivity;
import com.ivanbo97.mqttclient.util.ApplicationConstants;

import java.util.List;

public class PlantApiConnector {

    private List<String> jsonResponses;
    private PlantApiResponseListener apiListener;
    private PlantApiErrorListener errorListener;
    private JsonObjectRequest jsonObjectRequest;
    private RequestQueue requestQueue;

    public PlantApiConnector(List<String> jsonResponses) {
        this.jsonResponses = jsonResponses;
        apiListener = new PlantApiResponseListener(jsonResponses);
        errorListener = new PlantApiErrorListener();
        requestQueue = Volley.newRequestQueue(MainActivity.getMainActivityContext());
    }

    public void connect(){
        String plantForSearching = "apple";
        String url = ApplicationConstants.PLANT_API_URL + ApplicationConstants.USER_TOKEN + ApplicationConstants.QUERY_PARAM + plantForSearching;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, apiListener, errorListener);
        requestQueue.add(jsonObjectRequest);
    }
}
