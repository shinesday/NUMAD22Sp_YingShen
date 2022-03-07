package edu.neu.madcourse.numad22sp_yingshen;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Weather {

    private String temperature;
    private String weatherType;
    private String cityName;

    public static Weather fromJson(JSONObject jsonObject) {

        try
        {
            Weather weather = new Weather();
            weather.cityName=jsonObject.getString("name");
            weather.weatherType=jsonObject.getJSONArray("weather").getJSONObject(0).getString("main");
            weather.temperature=jsonObject.getJSONObject("main").getString("temp");
            return weather;
        }


        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    public String getTemperature() {
        return temperature;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public String getCityName() {
        return cityName;
    }

}
