package ai.retail.nimontron.helper;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import ai.retail.nimontron.utils.Constants;


/**
 * This class provides all the necessary things that are needed to work with location
 */
public class LocationManager2 extends LiveData<Location> {


    /**
     * Declared {@link LocationManager2} member variables
     */
    private static final String TAG = "LocationManager";
    private static final long LOCATION_INTERVAL = 5000;
    private static final long FASTEST_LOCATION_INTERVAL = 2000;
    private LocationRequest mLocationRequest;
    private int MAX_RETRY = 7;
    private int mCurrentCount;
    private Location mBestLocation;

    private final LocationCallback mLocationCallback;
    private final FusedLocationProviderClient mFusedLocationProviderClient;

    public LocationManager2(Context context) {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);

        mLocationCallback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

                Log.d(TAG, "connectLocationReque: "+locationResult.getLocations());
                for (Location location: locationResult.getLocations()){
                    if (location !=null){
                        Log.d(TAG, "connectLocationProvider: "+location);
//                        setValue(location);

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
                }
            }
        };
    }


    private void onConnected() {
        if (hasActiveObservers()) {
            mCurrentCount = 0;
            mBestLocation = null;
            mLocationRequest = new LocationRequest();
            mLocationRequest.setInterval(LOCATION_INTERVAL);
            mLocationRequest.setFastestInterval(FASTEST_LOCATION_INTERVAL);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        }
    }


    @Override
    protected void onActive() {
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
     * Called when location is changed
     * @param location as {@link Location} instance
     */
//    @Override
//    public void onLocationChanged(Location location) {
//        Log.d(TAG, "onLocationChanged: Latitude " + location.getLatitude() + "\nLongitude " + location.getLongitude() + "\nAccuracy " + location.getAccuracy());
//        // Deliver the location changes
//        if (mBestLocation == null) mBestLocation = location;
//        else if (mBestLocation.getAccuracy() > location.getAccuracy())
//            mBestLocation = location;
//        mCurrentCount++;
//
//        if (location.getAccuracy() <= Constants.LOCATION_ACCURACY_THRESHOLD)
//            setValue(location);
//        else if (mCurrentCount >= MAX_RETRY) {
//            mBestLocation.setElapsedRealtimeNanos(location.getElapsedRealtimeNanos());
//            setValue(mBestLocation);
//        }
//    }

//    /**
//     * Called when connection is suspended
//     * @param cause as integer
//     */
//    @Override
//    public void onConnectionSuspended(int cause) {
//    }
//
//    /**
//     * Called when connection is failed
//     * Requests from connection
//     * @param connectionResult as an instance of {@link ConnectionResult}
//     */
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//        connectLocationRequest();
//    }

    /**
     * Sets connection with google api client
     */


    public void connectLocationRequest() {
        Log.d(TAG, "connectLocationRequest: ");
        onConnected();
        mFusedLocationProviderClient.requestLocationUpdates(mLocationRequest,mLocationCallback,null);
    }

    /**
     * Removes connection with google api client
     */
    public void disconnectLocationRequest() {
        Log.d(TAG, "disconnectLocationRequest: ");
        mFusedLocationProviderClient.removeLocationUpdates(mLocationCallback);

    }

    /**
     * Sets maximum try for location
     * @param retry as integer
     */


    public void setMaxRetry(int retry) {
        this.MAX_RETRY = retry;
    }
}
