package com.developerbyweekend.bunker.models;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.developerbyweekend.bunker.R;
import com.developerbyweekend.bunker.api.APIService;
import com.developerbyweekend.bunker.api.Callable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by sunit on 19/11/16.
 */

public class User{

    //Preference
    private static final String PREF_USER = "user";

    //Api parameters
    private static final String API_PARAMETER_USERNAME = "username";
    private static final String API_PARAMETER_ID = "id";
    private static final String API_PARAMETER_PASSWORD = "password";
    private static final String API_PARAMETER_PASSWORD1 = "password1";
    private static final String API_PARAMETER_PASSWORD2 = "password2";
    private static final String API_PARAMETER_TYPE = "type";
    private static final String API_PARAMETER_DEVICE_ID = "device_id";
    private static final String API_PARAMETER_EMAIL = "email";
    private static final String API_PARAMETER_TOKEN= "token";
    private static final String API_HEADER_AUTHORIZATION = "Authorization";

    private String id;
    private String username;
    private String email;
    private String type;
    private String deviceId;
    private String token;
    private String password;

    public User() {
        //Required Fields
        this.username = this.password = this.token = this.deviceId = this.id = null ;

        //Optional Fields
        this.email = "";

        //Default Fields
        this.type = "employee";
    }

    public User(String username, String deviceID, String userType, String email,String password) {
        this.username = username;
        this.deviceId = deviceID;
        this.type = userType;
        this.email = email;
        this.password = password;
        this.token = null;
        this.id = null;

        if(this.type==null || this.type==""){
            this.type = "employee";
        }
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", userType='" + type + '\'' +
                ", deviceID='" + deviceId + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String device_id) {
        this.deviceId = device_id;
    }

    public String getToken() {
        return token;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public static void login(Context context, String username, String password, final Callable callable){
        try {

            JSONObject body= new JSONObject();
            body.put(User.API_PARAMETER_USERNAME,username);
            body.put(User.API_PARAMETER_PASSWORD,password);

            APIService.post(context, context.getString(R.string.LOGIN_API), body, new Callable() {
                @Override
                public void onResponse(Object data) {
                    callable.onResponse(parseLoginJson((JSONObject)data));
                }

                @Override
                public void onError(Exception error) {
                    callable.onError(error);
                }
            });

        }catch (Exception e){
            callable.onError(e);
        }

    }

    public void register(Context context,final Callable callable){
        if(this.username == null || this.password == null || this.type == null || this.deviceId ==null){
            callable.onError(new Exception("[username,password,type,deviceId] these fields are required."));
            return;
        }
        try{
            JSONObject body = new JSONObject();
            body.put(API_PARAMETER_USERNAME,this.username);
            body.put(API_PARAMETER_PASSWORD1,this.password);
            body.put(API_PARAMETER_PASSWORD2,this.password);
            if(this.email!=null && !this.email.equals("")) {
                body.put(API_PARAMETER_EMAIL, this.email);
            }
            body.put(API_PARAMETER_DEVICE_ID,this.deviceId);
            body.put(API_PARAMETER_TYPE,this.type);

            APIService.post(context, context.getString(R.string.REGISTRATION_API), body, new Callable() {
                @Override
                public void onResponse(Object data) {
                   try {
                       callable.onResponse(parseJson((JSONObject)data));
                   }catch (Exception e){
                       callable.onError(e);
                   }
                }

                @Override
                public void onError(Exception error) {
                    callable.onError(error);
                }
            });

        }catch (Exception e){
            callable.onError(e);
        }

    }

    public void update(){
    }

    public void getDetails(Context context, final Callable callable){

        try {
            if(this.token == null){
                callable.onError(new Exception("Token not set."));
                return;
            }

            //Headers
            HashMap<String,String> headers = new HashMap<String,String>();
            headers.put(User.API_HEADER_AUTHORIZATION,"Token "+this.token);

            APIService.list(context, context.getString(R.string.REGISTRATION_API), headers, new Callable() {
                @Override
                public void onResponse(Object data) {
                    JSONArray user_array = (JSONArray)data;
                    if(user_array.length()<1){
                        callable.onError(new Exception("Bad response from server. Return multiple users."));
                    }
                    try {
                       callable.onResponse(parseJson((JSONObject) user_array.get(0)));

                    }catch (Exception e){
                        callable.onError(e);
                    }

                }

                @Override
                public void onError(Exception error) {
                    callable.onError(error);
                }
            });
        }catch (Exception e){
            callable.onError(e);
        }
    }


    public void saveLocal(Activity activity){
        String json = this.getJson();
        SharedPreferences sharedPref = activity.getSharedPreferences(User.PREF_USER,0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(PREF_USER,json);
        editor.apply();
    }


    public static User getUserFromLocal(Activity activity){
        SharedPreferences pref = activity.getSharedPreferences(User.PREF_USER,0);
        String userString = pref.getString(User.PREF_USER,"");
        if(userString.equals("")){
            return null;
        }
        try {
            return parseLoginJson(new JSONObject(userString));
        }catch (JSONException e){
            return  null;
        }

    }

    //******** Utils **************



    protected static User parseLoginJson(JSONObject response){
        try {
            String token = response.getString(API_PARAMETER_TOKEN);
            User user = new User();
            user.token = token;
            return user;
        }catch (JSONException e){
            return null;
        }

    }

    protected User parseJson(JSONObject object) throws JSONException{

        User user = new User();
        user.username = object.getString(User.API_PARAMETER_USERNAME);
        user.email = object.getString(User.API_PARAMETER_EMAIL);
        user.deviceId = object.getString(User.API_PARAMETER_DEVICE_ID);
        user.token = object.getString(User.API_PARAMETER_TOKEN);
        user.id = object.getString(User.API_PARAMETER_ID);
        user.type = object.getString(User.API_PARAMETER_TYPE);
        return user;
    }

    private String getJson(){

        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("token", this.token);
            jsonObject.put("username", this.username);
            jsonObject.put("email", this.email);
            jsonObject.put("type",this.type);
            jsonObject.put("device_id",this.deviceId);

            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

}
