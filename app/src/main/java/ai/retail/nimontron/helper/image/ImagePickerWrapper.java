package ai.retail.nimontron.helper.image;

import android.graphics.Bitmap;
import android.net.Uri;

/**
 * Used to capture image
 */
public interface ImagePickerWrapper {

    /**
     * Declared {@link ImagePickerWrapper} member variables
     */
    int REQUEST_CODE_PICK_FROM_CAMERA = 1111;
    int REQUEST_CODE_PICK_FULL_IMAGE_FROM_CAMERA = 4444;
    String TEMP_IMAGE_FILE_NAME = "temp_photo.jpg";
    String KEY_INTENT_DATA = "data";
    int REQUEST_CODE_TAKEPICTURE_IMG = 1111;


    /**
     * Captures image
     * @return String file path
     * @param filePrefix as string
     * @param requestCode as integer
     */
    String captureFullSizeImage(String filePrefix, int requestCode);

    /**
     * Captures image with front camera
     * @return String file path
     * @param filePrefix as string
     * @param requestCode as integer
     */
    String captureFullSizeImageWithFrontCam(String filePrefix, int requestCode);

    /**
     * Gets byte form of {@link Bitmap}
     * @param bitmap
     * @return byte array
     */
    byte[] getByteFromBitmap(Bitmap bitmap);

    /**
     * Gets uri from bitmap
     * @param bitmap as {@link Bitmap} instance
     * @return an instance of {@link Uri}
     */
    Uri getUriFromBitmap(Bitmap bitmap);

    /**
     * Stops write permission
     * @param uri as {@link Uri} instance
     */
    void revokeWritePermission(Uri uri);
}
