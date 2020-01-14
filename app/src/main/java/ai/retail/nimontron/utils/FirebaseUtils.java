package ai.retail.nimontron.utils;

import android.net.Uri;

public class FirebaseUtils {
    private static final String TAG = "FirebaseUtils";

    public static String getImagePath(Uri imageUri) {
        return imageUri.toString();
    }

}
