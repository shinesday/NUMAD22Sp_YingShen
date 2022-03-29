package edu.neu.madcourse.numad22sp_yingshen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class AtYourService extends AppCompatActivity {
    private final String appID = "2b7f313d41bf1aeeb4328d5d6cdca858";
    private final String weatherUrl = "https://api.openweathermap.org/data/2.5/weather";
    String location = LocationManager.GPS_PROVIDER;
    LocationManager locationManager;
    LocationListener locationListener;
    private ImageView weatherImage;
    private TextView weatherTemperature;
    private TextView weatherCity;
    private TextView weatherType;
    private TextView notification;
    private Button getWeather;

    final long MIN_TIME = 5000;
    final float MIN_DISTANCE = 1000;
    final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_at_your_service);

        weatherImage = findViewById(R.id.weather_image);
        weatherTemperature = findViewById(R.id.weather_temperature);
        weatherCity = findViewById(R.id.weather_city);
        weatherType = findViewById(R.id.weather_type);
        getWeather = findViewById(R.id.btn_find_weather);
        notification = findViewById(R.id.notification);

        getWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notification.setText("Please Wait");
                getCurrentWeather();
            }
        });
    }

    private void getCurrentWeather() {

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                String Latitude = String.valueOf(location.getLatitude());
                String Longitude = String.valueOf(location.getLongitude());

                RequestParams params = new RequestParams();
                params.put("lat", Latitude);
                params.put("lon", Longitude);
                params.put("appid", appID);
                params.put("units", "imperial");
                connectToWeb(params);

            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(AtYourService.this,"Try Again After Location Permission Granted",Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        locationManager.requestLocationUpdates(location, MIN_TIME, MIN_DISTANCE, locationListener);
    }

    private  void connectToWeb(RequestParams params) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(weatherUrl,params,new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Toast.makeText(AtYourService.this,"Updated Weather",Toast.LENGTH_SHORT).show();

                Weather weather= Weather.fromJson(response);
                updateWeather(weather);
                notification.setText("");
            }
        });

    }

    private  void updateWeather(Weather weather){
        weatherTemperature.setText(weather.getTemperature() + "°F");
        weatherCity.setText(weather.getCityName());
        weatherType.setText(weather.getWeatherType());

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(locationManager!=null)
        {
            locationManager.removeUpdates(locationListener);
        }
    }

}
