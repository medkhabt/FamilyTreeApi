package com.medkha.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medkha.service.LocationService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LocationGeoapifyService implements LocationService {
    private static LocationService instance = null;
    private final String __URL__ = "https://api.geoapify.com/v1/geocode/";
    private final String __API_KEY__ = "561ea75d15314816934abb4322f5e3a7";

    private LocationGeoapifyService() {
    }

    @Override
    public Boolean isValidCity(String city) {
        try {
            URL url = new URL(__URL__ + "search?city=" + city + "&type=city" + "&apiKey="+ __API_KEY__ );
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setRequestProperty("Accept", "application/json");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(http.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            String responseJsonStr = content.toString();
            http.disconnect();

            Map<String, Object> mapping = new ObjectMapper().readValue(responseJsonStr, HashMap.class);
            if(((ArrayList)mapping.get("features")).isEmpty()) {
                return false;
            }
            return true;

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static LocationService getInstance(){
        if(instance == null) {
            instance = new LocationGeoapifyService();
        }
        return instance;
    }
}
