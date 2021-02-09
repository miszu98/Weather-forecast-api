package com.weatherforecast.weatherapp.services;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;


@Service
public class weatherservice {

    private String API_KEY = "YOUR_API_KEY"; // you must generate your api key :D
    private String url = "http://api.openweathermap.org/data/2.5/weather?";
    private String city = "Warszawa";
    private String filledUrl = url + "appid=" + API_KEY + "&q=" + city;

    public JsonObject getJson() throws IOException {
        filledUrl = url + "appid=" + API_KEY + "&q=" + getCity();
        URL link = new URL(filledUrl);
        URLConnection request = link.openConnection();
        request.connect();
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject rootobj = root.getAsJsonObject();
        return rootobj;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWeatherDescription() throws IOException {
        return String.valueOf(getJson().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description"));
    }

    public String getTemp() throws IOException {
        return Math.round(Double.parseDouble(String.valueOf(getJson().get("main").getAsJsonObject().get("temp"))) - 273.15) + " ℃";
    }

    public String getTempFeelsLike() throws IOException {
        return Math.round(Double.parseDouble(String.valueOf(getJson().get("main").getAsJsonObject().get("feels_like"))) - 273.15) + " ℃";
    }

    public String getTempMin() throws IOException {
        return Math.round(Double.parseDouble(String.valueOf(getJson().get("main").getAsJsonObject().get("temp_min"))) - 273.15) + " ℃";
    }

    public String getTempMax() throws IOException {
        return Math.round(Double.parseDouble(String.valueOf(getJson().get("main").getAsJsonObject().get("temp_max"))) - 273.15) + " ℃";
    }

    public String getPressure() throws IOException {
        return getJson().get("main").getAsJsonObject().get("pressure") + " hPa";
    }

    public String getHumidity() throws IOException {
        return getJson().get("main").getAsJsonObject().get("humidity") + "%";
    }

    public String getWindSpeed() throws IOException {
        return getJson().get("wind").getAsJsonObject().get("speed") + " km/h";
    }



}
