package ai.retail.nimontron.helper;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import ai.retail.nimontron.R;
import com.google.android.material.snackbar.Snackbar;

/**
 * Checks all the permissions which are needed for the project
 */
public class PermissionManager {

    /**
     * Declared {@link PermissionManager} member variables
     */
    public static final int ALL_PERMISSION_REQUEST_CODE = 100;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 200;
    public static final int CAMERA_PERMISSION_REQUEST_CODE = 600;
    private static final int REQUEST_SETTINGS = 444;
    public static final int PHONE_STATE_PERMISSION_REQUEST_CODE = 500;


    /**
     * Checks location permission
     * @param context as {@link Activity}
     * @return boolean value
     */
    public boolean hasLocationPermission(Activity context) {
        return hasCoarseLocationPermission(context) && hasFineLocationPermission(context);
    }

    /**
     * Checks coarse location permission
     * @param context as {@link Activity}
     * @return boolean value
     */
    public boolean hasCoarseLocationPermission(Activity context) {
        return checkPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    /**
     * Checks fine location permission
     * @param context as {@link Activity}
     * @return boolean value
     */
    public boolean hasFineLocationPermission(Activity context) {
        return checkPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    /**
     * Checks phone state permission
     * @param context as {@link Activity}
     * @return boolean value
     */
    public boolean hasPhoneStatePermission(Activity context) {
        return checkPermission(context, Manifest.permission.READ_PHONE_STATE);
    }

    /**
     * Checks camera permission
     * @param context as {@link Activity}
     * @return boolean value
     */
    public boolean hasCameraPermission(Activity context) {
        return checkPermission(context, Manifest.permission.CAMERA);
    }


    /**
     * <p>Returns true if the user has previously denied the request,
     * and returns false if a user has denied a permission and selected the Don't ask again option in the permission request dialog,
     * or if a device policy prohibits the permission.</p>
     * @param context as {@link Activity}
     * @return boolean value
     */
    public boolean shouldShowCameraPermissionRationale(Activity context) {
        return ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.CAMERA);
    }

    /**
     * <p>Returns true if the user has previously denied the request,
     * and returns false if a user has denied a permission and selected the Don't ask again option in the permission request dialog,
     * or if a device policy prohibits the permission.</p>
     * @param context as {@link Activity}
     * @return boolean value
     */
    public boolean shouldShowPhoneStatePermissionRationale(Activity context) {
        return ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.READ_PHONE_STATE);
    }

    /**
     * <p>Returns true if the user has previously denied the request,
     * and returns false if a user has denied a permission and selected the Don't ask again option in the permission request dialog,
     * or if a device policy prohibits the permission.</p>
     * @param context as {@link Activity}
     * @return boolean value
     */
    public boolean shouldShowLocationPermissionRationale(Activity context) {
        return ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    /**
     * Checks camera permission and location permission
     * @param activity as instance of {@link AppCompatActivity}
     * @return boolean value
     */
    public boolean hasAllInitialPermission(AppCompatActivity activity) {
        return hasCameraPermission(activity) && hasLocationPermission(activity);
    }

    /**
     * Checks permissions
     * @param context as {@link Context}
     * @param permission as String
     * @return boolean value
     */
    private boolean checkPermission(Context context, String permission) {
        try {
            return !(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Requests for all initial permissions
     * @param ctx as {@link Activity}
     */
    public void requestForAllInitialPermission(Activity ctx) {
        if (ctx != null) {
            ActivityCompat.requestPermissions(ctx,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.CAMERA},
                    ALL_PERMISSION_REQUEST_CODE);
        }
    }

    /**
     * Requests for location permission
     * @param ctx as {@link Activity}
     */
    public void requestForLocationPermission(Activity ctx) {
        ActivityCompat.requestPermissions(ctx,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                LOCATION_PERMISSION_REQUEST_CODE);
    }

    /**
     * Requests for camera permission
     * @param ctx as {@link Activity}
     */
    public void requestForCameraPermission(Activity ctx) {
        requestPermissions((AppCompatActivity) ctx, CAMERA_PERMISSION_REQUEST_CODE, ctx.getString(R.string.text_provide_camera_permission), Manifest.permission.CAMERA);
    }

    /**
     * Requests for phone state permission
     * @param activity as {@link Activity}
     */
    public void requestForPhoneState(Activity activity) {
        requestPermissions((AppCompatActivity) activity, PHONE_STATE_PERMISSION_REQUEST_CODE, activity.getString(R.string.text_provide_phone_state_permission), Manifest.permission.READ_PHONE_STATE);
    }

    /**
     * Request permission set for location
     */
    public void requestPermissions(AppCompatActivity activity, int requestCode, String message, String... permissions) {
        boolean showSnackBar = true;
        for (String permission : permissions) {
            showSnackBar = showSnackBar & ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
        }
        if (showSnackBar) {
            showSettingsSnackbar(message, activity);
        } else {
            ActivityCompat.requestPermissions(activity, permissions, requestCode);
        }
    }

    /**
     * Request for permissions
     * @param activity as {@link AppCompatActivity}
     * @param requestCode as integer
     * @param message as String
     * @param permission as String
     */
    public void requestPermissions(AppCompatActivity activity, int requestCode, String message, String permission) {
        ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
    }

    /**
     * Show snack bar for setting
     * @param message as String
     * @param activity as {@link Activity}
     */
    public void showSettingsSnackbar(String message, final Activity activity) {
        if (activity != null) {
            View view = activity.findViewById(android.R.id.content);
            Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                    .setAction(activity.getString(R.string.settings), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startPermissionScreen(activity);
                        }
                    })
                    .setActionTextColor(ContextCompat.getColor(activity, R.color.colorPrimary))
                    .show();
        }
    }

    /**
     * Starts permission screen
     * @param activity as {@link Activity}
     */

    public void startPermissionScreen(final Activity activity) {
        Intent settingsIntent = new Intent();
        settingsIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        settingsIntent.addCategory(Intent.CATEGORY_DEFAULT);
        settingsIntent.setData(Uri.parse("package:" + activity.getPackageName()));
        settingsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        settingsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        settingsIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        activity.startActivityForResult(settingsIntent, REQUEST_SETTINGS);
    }
}
