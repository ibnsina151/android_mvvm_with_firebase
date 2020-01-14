package ai.retail.nimontron.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import ai.retail.nimontron.utils.Constants;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * This class provides all the necessary things that are needed to work with location
 */
public class LocationManager extends LiveData<Location> implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    /**
     * Declared {@link LocationManager} member variables
     */
    private static final String TAG = "LocationManager";
    private static final long LOCATION_INTERVAL = 5000;
    private static final long FASTEST_LOCATION_INTERVAL = 2000;
    private GoogleApiClient googleApiClient;
    private LocationRequest mLocationRequest;
    private int MAX_RETRY = 7;
    private int mCurrentCount;
    private Location mBestLocation;

    /**
     * {@link LocationManager} constructor
     * Creates google api client
     * @param context as {@link Context}
     */
    public LocationManager(Context context) {
        googleApiClient = new GoogleApiClient.Builder(context, this, this)
                .addApi(LocationServices.API)
                .build();
    }

    /**
     * Called when we need to connect location request
     */
    @Override
    protected void onActive() {
        // Wait for the GoogleApiClient to be connected
        connectLocationRequest();
    }

    /**
     * Called when we need to disconnect location request
     */
    @Override
    protected void onInactive() {
        disconnectLocationRequest();
    }

    /**
     * Called when connected
     * @param connectionHint a {@link Bundle} object
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onConnected(Bundle connectionHint) {
        // Try to immediately find a location
        /*Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (lastLocation != null) {
            setValue(lastLocation);
        }*/
        // Request updates if there’s someone observing
        if (hasActiveObservers()) {
            mCurrentCount = 0;
            mBestLocation = null;
            mLocationRequest = new LocationRequest();
            mLocationRequest.setInterval(LOCATION_INTERVAL);
            mLocationRequest.setFastestInterval(FASTEST_LOCATION_INTERVAL);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, mLocationRequest, this);
        }
    }

    /**
     * Called when location is changed
     * @param location as {@link Location} instance
     */
    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged: Latitude " + location.getLatitude() + "\nLongitude " + location.getLongitude() + "\nAccuracy " + location.getAccuracy());
        // Deliver the location changes
        if (mBestLocation == null) mBestLocation = location;
        else if (mBestLocation.getAccuracy() > location.getAccuracy())
            mBestLocation = location;
        mCurrentCount++;

        if (location.getAccuracy() <= Constants.LOCATION_ACCURACY_THRESHOLD)
            setValue(location);
        else if (mCurrentCount >= MAX_RETRY) {
            mBestLocation.setElapsedRealtimeNanos(location.getElapsedRealtimeNanos());
            setValue(mBestLocation);
        }
    }

    /**
     * Called when connection is suspended
     * @param cause as integer
     */
    @Override
    public void onConnectionSuspended(int cause) {
    }

    /**
     * Called when connection is failed
     * Requests from connection
     * @param connectionResult as an instance of {@link ConnectionResult}
     */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        connectLocationRequest();
    }

    /**
     * Sets connection with google api client
     */
    public void connectLocationRequest() {
        Log.d(TAG, "connectLocationRequest: ");
        googleApiClient.connect();
    }

    /**
     * Removes connection with google api client
     */
    public void disconnectLocationRequest() {
        Log.d(TAG, "disconnectLocationRequest: ");
        if (googleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        }
        googleApiClient.disconnect();
    }

    /**
     * Sets maximum try for location
     * @param retry as integer
     */
    public void setMaxRetry(int retry) {
        this.MAX_RETRY = retry;
    }
}
