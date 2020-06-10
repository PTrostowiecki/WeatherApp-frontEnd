package com.weatherapp.frontend.configuration;

import com.weatherapp.frontend.model.TempUnits;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static com.weatherapp.frontend.model.TempUnits.*;


public class WeatherServiceConnection {

     private double temp;
     private String units;

    public double getTemperature (String city, String location, TempUnits tempUnits) throws JSONException {

        if(tempUnits == Kelvin){ units = "default";}
        if(tempUnits == Celsius){ units ="metric";}
        if(tempUnits == Fahrenheit){units = "imperial";}


        try {
            Request request = new Request.Builder()
                    .url("https://afternoon-forest-83004.herokuapp.com/forecast/getweather?city=" + city + "&location=" + location+"&temperatureUnits="+units)
                    .build();
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            ResponseBody responseBody = response.body();
            JSONObject jsonObject = new JSONObject(responseBody.string());
            double weatherTemp = jsonObject.getJSONObject("main").getDouble("temp");
            this.temp = weatherTemp;
           return this.temp;


        }catch (JSONException | IOException e){
            System.out.println("catch error");

        } return 0;

    }

    public double getTemp(){
        return this.temp;
    }

}
