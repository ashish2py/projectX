package com.developerbyweekend.bunker.api;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.developerbyweekend.bunker.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sunit on 20/11/16.
 */

public class APIService {

    private static RequestQueue app_request_queue = null;
    private static int BACKEND_URL = R.string.BACKEND_URL;


    private static RequestQueue getRequestQueue(Context context){
        if(app_request_queue == null){
            app_request_queue = Volley.newRequestQueue(context);
        }
        return  app_request_queue;
    }

    public static void get(Context context, String api, final Map<String,String> headers, final Callable callable){

        String url = context.getString(BACKEND_URL)+api;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url, null,new Response.Listener<JSONObject>(){

            @Override
            public void onResponse(JSONObject response){
                callable.onResponse(response);
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.networkResponse != null && error.networkResponse.data != null){
                    callable.onError(new Exception(new String(error.networkResponse.data)));
                }else{
                    callable.onError(error);
                }
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if(headers == null)
                    return new HashMap<String, String>();
                return headers;
            }
        };
    }

    public static void list(Context context, String api, final Map<String,String> headers, final Callable callable){

        String url = context.getString(BACKEND_URL)+api;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url, null,new Response.Listener<JSONArray>(){

            @Override
            public void onResponse(JSONArray response){
                callable.onResponse(response);
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.networkResponse != null && error.networkResponse.data != null){
                    callable.onError(new Exception(new String(error.networkResponse.data)));
                }else{
                    callable.onError(error);
                }
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if(headers == null)
                    return new HashMap<String, String>();
                return headers;
            }
        };
    }

    public static void post(Context context,String api, JSONObject body, final Callable callable){

        String url = context.getString(BACKEND_URL)+api;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, body, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                    callable.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.networkResponse != null && error.networkResponse.data != null){
                    callable.onError(new Exception(new String(error.networkResponse.data)));
                }else{
                    callable.onError(error);
                }
            }
        });
        APIService.getRequestQueue(context).add(request);
    }
    
    public static void patch(Context context,String api, JSONObject object,Callable callable{
        //Todo
    }

    public static void delete(Context context,String api,Callable callable{
        //Todo
    }
}
