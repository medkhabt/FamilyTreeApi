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
import java.util.List;
import java.util.Map;

public class LocationGeoapifyService implements LocationService {
    private static LocationService instance = null;
    private final String __URL__ = "https://api.geoapify.com/v1/geocode/";
    private final String __API_KEY__ = "561ea75d15314816934abb4322f5e3a7";

    private LocationGeoapifyService() {
    }

    @Override
    public Boolean isValidCity(String address) {
        String urlString ="";
        String[] split = address.split("\\s*,\\s*");
        if(split.length == 1) {
            urlString = __URL__ + "search?city=" + split[0] + "&type=city" + "&apiKey="+ __API_KEY__;
        }
        else if(split.length == 2){
            String countryCode = getCountryCode(split[1]);
            urlString = __URL__ + "search?city=" + split[0] + "&filter=countrycode:" + countryCode + "&type=city"+"&apiKey="+ __API_KEY__;
        }
        else {
            throw new IllegalArgumentException("Wrong address format " + address + ". Should be " +
                    "either \"city\" or \"city, country\".");
        }
        try {
            URL url = new URL(urlString);
            Map<String, Object> mapping = sendRequest(url);
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

    private String getCountryCode(String country) {
        try {
            URL url = new URL(__URL__ + "search?country=" + country + "&apiKey=" + __API_KEY__);
            Map<String, Object> mapping = sendRequest(url);
            List<Object> features = ((ArrayList<Object>)(mapping.get("features")));
            if(features.isEmpty()) {
                throw new IllegalArgumentException("Couldn't find a country code for country: " + country);
            }
            else{
                Map<String, Object> properties = (Map<String, Object>) ((Map<String, Object>)features.get(0)).get("properties");
                return (String)properties.get("country_code");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, Object> sendRequest(URL url) throws IOException {
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
        return new ObjectMapper().readValue(responseJsonStr, HashMap.class);
    }



    public static LocationService getInstance(){
        if(instance == null) {
            instance = new LocationGeoapifyService();
        }
        return instance;
    }
}
