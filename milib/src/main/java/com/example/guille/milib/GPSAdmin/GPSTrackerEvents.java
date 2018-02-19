package com.example.guille.milib.GPSAdmin;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by guille on 15/1/18.
 */

public class GPSTrackerEvents implements LocationListener {

    GPSTracker gpsTracker;

    public GPSTrackerEvents(GPSTracker gpsTracker) {
        this.gpsTracker=gpsTracker;
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.v("GPSTrackerEvents", "Cambio posicion"+location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
