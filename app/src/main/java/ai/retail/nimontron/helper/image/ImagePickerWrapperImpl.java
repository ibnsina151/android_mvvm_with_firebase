package ai.retail.nimontron.helper.image;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;

import ai.retail.nimontron.utils.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static android.hardware.Camera.CameraInfo.CAMERA_FACING_BACK;
import static android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT;

/**
 * Implementation of {@link ImagePickerWrapper}
 */
public class ImagePickerWrapperImpl implements ImagePickerWrapper {

    /**
     * Declared {@link ImagePickerWrapperImpl} member variables
     */
    private static final String CAMERA_FACING_EXTRA_KEY = "android.intent.extras.CAMERA_FACING";
    private final Activity mActivity;

    /**
     * {@link ImagePickerWrapperImpl} constructor
     * @param activity
     */
    public ImagePickerWrapperImpl(Activity activity) {
        mActivity = activity;
    }

    /**
     * Captures image
     * @return String file path
     * @param id as string
     * @param requestCode as integer
     */

    @Override
    public String captureFullSizeImage(String id, int requestCode) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureIntent.putExtra(CAMERA_FACING_EXTRA_KEY, CAMERA_FACING_BACK);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(mActivity.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = FileUtils.createImageFile(id, mActivity);
            } catch (IOException ex) {

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                String authority = mActivity.getPackageName() + ".fileprovider";
                Uri photoURI = FileProvider.getUriForFile(mActivity,
                        authority,
                        photoFile);
                grantAppPermission(mActivity, takePictureIntent, photoURI);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                mActivity.startActivityForResult(takePictureIntent, requestCode);
                return photoFile.getAbsolutePath();
            }
        }
        return null;
    }

    /**
     * Captures image with front camera
     * @return String file path
     * @param filePrefix as string
     * @param requestCode as integer
     */
    @Override
    public String captureFullSizeImageWithFrontCam(String filePrefix, int requestCode) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureIntent.putExtra(CAMERA_FACING_EXTRA_KEY, CAMERA_FACING_FRONT);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(mActivity.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = FileUtils.createImageFile(filePrefix, mActivity);
            } catch (IOException ex) {

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                String authority = mActivity.getPackageName() + ".fileprovider";
                Uri photoURI = FileProvider.getUriForFile(mActivity,
                        authority,
                        photoFile);
                grantAppPermission(mActivity, takePictureIntent, photoURI);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                mActivity.startActivityForResult(takePictureIntent, requestCode);
                return photoFile.getAbsolutePath();
            }
        }
        return null;
    }

    /**
     * Gets byte form of {@link Bitmap}
     * @param bitmap
     * @return byte array
     */
    @Override
    public byte[] getByteFromBitmap(Bitmap bitmap) {
        if (bitmap == null) return null;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    /**
     * Gets uri from bitmap
     * @param bitmap as {@link Bitmap} instance
     * @return an instance of {@link Uri}
     */
    @Override
    public Uri getUriFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(mActivity.getContentResolver(), bitmap, TEMP_IMAGE_FILE_NAME, null);
        return Uri.parse(path);
    }

    private void grantAppPermission(Context context, Intent intent, Uri fileUri) {
        List<ResolveInfo> resolvedIntentActivities = context.getPackageManager()
                .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

        for (ResolveInfo resolvedIntentInfo : resolvedIntentActivities) {
            String packageName = resolvedIntentInfo.activityInfo.packageName;
            context.grantUriPermission(packageName, fileUri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
    }

    /**
     * Stops write permission
     * @param uri as {@link Uri} instance
     */
    public void revokeWritePermission(Uri uri) {
        mActivity.revokeUriPermission(uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
    }
}
