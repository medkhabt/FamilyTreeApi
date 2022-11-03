package com.medkha.service;

import com.medkha.service.impl.LocationGeoapifyService;

public class LocationServices {
    public static LocationService getLocationService() {
        return LocationGeoapifyService.getInstance();
    }
}
