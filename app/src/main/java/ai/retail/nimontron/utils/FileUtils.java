package ai.retail.nimontron.utils;

import android.app.Activity;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class FileUtils {
    public static void deleteFile(String imagePath) {
        if (TextUtils.isEmpty(imagePath)) return;
        File file = new File(imagePath);
        file.delete();
    }

    public static File createImageFile(String userPhone, Activity activity) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        int randomNum = new Random().nextInt(9000) + 1000;
        String imageFileName = userPhone + "_JPEG_" + timeStamp + "_" + randomNum;
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                Constants.IMAGE_FILE_SUFFIX,  /* suffix */
                storageDir      /* directory */
        );
        return image;
    }

    public static void deleteAllFile(List<String> filePaths) {
        for (String filePath :
                filePaths) {
            deleteFile(filePath);
        }
    }

    public static boolean isUrl(String filePath) {
        return filePath.startsWith(Constants.URL_PREFIX);
    }
}
